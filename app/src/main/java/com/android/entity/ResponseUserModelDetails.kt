package com.android.entity

data class ResponseUserModelDetails(
    val authorities: List<Authority>,
    val email: String,
    val firstName: String,
    val gender: String,
    val id: String,
    val imageUrl: String,
    val lastName: String,
    val mobile: String,
    val name: String,
    val otp: Int,
    val provider: String,
    val providerId: String,
    val username: String
)