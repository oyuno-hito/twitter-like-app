package com.example.twitter_like.controller

import com.example.twitter_like.model.HelloWorldResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController {
    @GetMapping("/hello_world")
    fun helloWorld(): HelloWorldResponse {
        return HelloWorldResponse(value = "Hello World")
    }
}
