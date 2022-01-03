package com.example.twitter_like.enum

/**
 * ロールIDの定数クラス
 * migratorでの定義に準拠
 */
enum class Role(val id: Int, val roleName: String) {
    ADMIN(1, "管理者"),
    DEVELOPER(2, "開発者"),
    USER(3, "ユーザー")
}
