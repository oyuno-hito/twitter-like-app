package com.example.twitter_like.repository

import com.example.twitter_like.model.LoginUser
import org.springframework.stereotype.Component

@Component
class UserRepositoryImpl: UserRepository {
    override fun findByLoginId(loginId: String): LoginUser {
        // TODO: DBを使った認証
        return LoginUser(
            loginId = loginId
        )
    }
}
