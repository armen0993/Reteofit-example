package com.android.retrofit

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService(private val context: Context) {

    val retrofit = Retrofit.Builder().baseUrl("https://trip-share-api.servicefinder.am")
        .client(getClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun getClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(InterceptorToken(context)).build()
        return okHttpClient
    }
}

class InterceptorToken(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val responce = chain.request().newBuilder()
        val pref = context.getSharedPreferences("sharedKey", Context.MODE_PRIVATE)
        val token = pref.getString("currentKey", "")
        if (!token.isNullOrEmpty()) {
            responce.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(responce.build())

    }

}