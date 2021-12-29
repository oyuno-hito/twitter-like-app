package com.example.twitter_like.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


/**
 * Spring Security 設定
 */
@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    companion object {
        const val LOGIN_URL = "/login"
        const val LOGOUT_URL = "/logout"
        const val USER_NAME_PARAMETER = "username"
        const val PASSWORD_PARAMETER = "password"
    }

    override fun configure(http: HttpSecurity) {
        //認証不要エンドポイント
        http
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/hello_world").permitAll()
            // ログイン
            .and()
            .formLogin()
            .loginProcessingUrl(LOGIN_URL).permitAll()
            .usernameParameter(USER_NAME_PARAMETER)
            .passwordParameter(PASSWORD_PARAMETER)
            // ログアウト
            .and()
            .logout()
            .logoutUrl(LOGOUT_URL)
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
        // CSRFの設定
        http.csrf().disable()
        // CORSの設定
        http.cors().configurationSource(corsConfigurationSource())
    }

    /**
     * パスワードエンコーダー
     */
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        // TODO: DBを使用するようになったらBCryptPasswordEncoder()に切り替える
        return NoOpPasswordEncoder.getInstance()
        // return BCryptPasswordEncoder()
    }

    /**
     * CORSの設定
     */
    private fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL)
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL)
        corsConfiguration.addAllowedOrigin("*")
        corsConfiguration.allowCredentials = true

        val corsConfigurationSource = UrlBasedCorsConfigurationSource()
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration)

        return corsConfigurationSource
    }
}
