package com.example.twitter_like.repository

import com.example.twitter_like.model.LoginUser

interface UserRepository {
    /**
     * ログインIDからユーザー情報を取得する
     *
     * @param loginId
     * @return ユーザー情報
     */
    fun findByLoginId(loginId: String): LoginUser
}
