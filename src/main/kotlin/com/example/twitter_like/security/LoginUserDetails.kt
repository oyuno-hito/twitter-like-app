package com.example.twitter_like.security

import com.example.twitter_like.model.LoginUser
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

class LoginUserDetails(
    private val loginUser: LoginUser
): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return AuthorityUtils.createAuthorityList(loginUser.role)
    }
    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return loginUser.loginId
    }

    override fun getPassword(): String {
        return loginUser.password
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    fun getUserDisplayName(): String? {
        return loginUser.name
    }
}
