package com.example.twitter_like.model

data class LoginUser(
    val userName: String,
    val password: String = "password", // TODO: 固定値の削除
    val role: String = "USER"
)
