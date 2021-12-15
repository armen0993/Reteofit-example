package com.android.retrofit

import com.android.entity.ResponseUserModelDetails
import com.android.entity.UserSignInRequestModel
import com.android.entity.UserSignInResponseModel

interface Repo {
    suspend fun signIn(requestModel: UserSignInRequestModel):UserSignInResponseModel?


    suspend fun getDetails():ResponseUserModelDetails?
}