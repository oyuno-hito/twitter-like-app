package com.example.twitter_like.repository

import com.example.twitter_like.mapper.UserMapper
import com.example.twitter_like.model.LoginUser
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class UserRepositoryImpl(
    private val userMapper: UserMapper
): UserRepository {
    override fun findByLoginId(loginId: String): LoginUser {
        return userMapper.findByLoginId(loginId)?: throw UsernameNotFoundException("ログインIDかパスワードが間違っています")
    }
}
