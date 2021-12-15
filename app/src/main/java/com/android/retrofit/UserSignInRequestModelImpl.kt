package com.android.retrofit

import android.content.Context
import android.util.Log
import com.android.entity.ResponseUserModelDetails
import com.android.entity.UserSignInRequestModel
import com.android.entity.UserSignInResponseModel
import com.android.network.SignInApi

class UserSignInRequestModelImpl(private val context: Context) : Repo {

    override suspend fun signIn(requestModel: UserSignInRequestModel): UserSignInResponseModel? {
        val response =
            RetrofitService(context).retrofit.create(SignInApi::class.java).signInUser(requestModel)
        Log.d("xxx", "${response.body()}")
        if (response.isSuccessful && response.body() != null) {

            val sharedPreference = context.getSharedPreferences("sharedKey", Context.MODE_PRIVATE)
            sharedPreference.edit().putString("currentKey",response.body()?.accessToken.toString()).apply()


            return response.body()
        }
        return null

    }


    override suspend fun getDetails(): ResponseUserModelDetails? {
     val responseDetails = RetrofitService(context).retrofit.create(SignInApi::class.java).getUserDetails()

        if (responseDetails.isSuccessful && responseDetails.body()!= null){
            return responseDetails.body()
        }
        return null
    }
}