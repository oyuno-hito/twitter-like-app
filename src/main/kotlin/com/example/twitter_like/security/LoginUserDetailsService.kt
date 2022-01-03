package com.example.twitter_like.security

import com.example.twitter_like.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * form認証のサービスクラス
 */
@Service
class LoginUserDetailsService(
    private val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        username?.let {
            val loginUser = userRepository.findByLoginId(loginId = username)
            return LoginUserDetails(loginUser)
        }?: throw UsernameNotFoundException("ユーザーが存在しません")
    }

}
