package com.example.twitter_like.model

data class LoginUser(
    val id: Int,
    val loginId: String,
    val password: String,
    val name: String?,
    val roleId: Int,
    val role: String
)
