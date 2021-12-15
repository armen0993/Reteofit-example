package com.android.network

import com.android.entity.ResponseUserModelDetails
import com.android.entity.UserSignInRequestModel
import com.android.entity.UserSignInResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SignInApi {
    @POST("/api/v1/auth/authenticate")
    suspend fun signInUser(@Body request: UserSignInRequestModel): Response<UserSignInResponseModel>

    @GET("/api/v1/user/data")
    suspend fun getUserDetails(): Response<ResponseUserModelDetails>
}