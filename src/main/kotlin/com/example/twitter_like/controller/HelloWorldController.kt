package com.example.twitter_like.controller

import com.example.twitter_like.model.HelloWorldResponse
import com.example.twitter_like.security.LoginUserDetails
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/hello_world")
@RestController
class HelloWorldController {
    @GetMapping("")
    fun helloWorld(): HelloWorldResponse {
        return HelloWorldResponse(value = "Hello World")
    }

    @GetMapping("everyone")
    fun helloUser(
        // ログイン中のユーザー情報
        @AuthenticationPrincipal
        loginUserDetails: LoginUserDetails?
    ): HelloWorldResponse {
        val userName = loginUserDetails?.username?: "guest"
        return HelloWorldResponse(value = "Hello $userName")
    }

    @GetMapping("logged_in_user")
    fun helloLoggedInUser(
        // ログイン中のユーザー情報
        @AuthenticationPrincipal
        loginUserDetails: LoginUserDetails
    ): HelloWorldResponse {
        val userName = loginUserDetails.username
        return HelloWorldResponse(value = "Hello $userName")
    }
}
