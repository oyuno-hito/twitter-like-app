package com.example.twitter_like.security

import com.example.twitter_like.model.LoginUser
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * form認証のサービスクラス
 */
@Service
class LoginUserDetailsService: UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        username?.let {
            // TODO: DBを使った認証
            val loginUser = LoginUser(userName = username)
            return LoginUserDetails(loginUser)
        }?: throw UsernameNotFoundException("ユーザーが存在しません")
    }

}
