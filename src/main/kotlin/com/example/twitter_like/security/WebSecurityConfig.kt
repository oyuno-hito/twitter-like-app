package com.example.twitter_like.security

import com.example.twitter_like.security.handler.SimpleAccessDeniedHandler
import com.example.twitter_like.security.handler.SimpleAuthenticationEntryPoint
import com.example.twitter_like.security.handler.SimpleAuthenticationFailureHandler
import com.example.twitter_like.security.handler.SimpleAuthenticationSuccessHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
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
            .antMatchers(HttpMethod.GET, "/hello_world","/hello_world/everyone").permitAll()
            .antMatchers(HttpMethod.GET, "/hello_world/logged_in_user").hasAnyAuthority("USER")
            // 例外処理
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint())
            .accessDeniedHandler(accessDeniedHandler())
            // ログイン
            .and()
            .formLogin()
            .loginProcessingUrl(LOGIN_URL).permitAll()
            .usernameParameter(USER_NAME_PARAMETER)
            .passwordParameter(PASSWORD_PARAMETER)
            .successHandler(authenticationSuccessHandler())
            .failureHandler(authenticationFailureHandler())
            // ログアウト
            .and()
            .logout()
            .logoutUrl(LOGOUT_URL)
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .logoutSuccessHandler(logoutSuccessHandler())
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

    /**
     * 例外処理
     */
    fun authenticationEntryPoint(): AuthenticationEntryPoint? {
        return SimpleAuthenticationEntryPoint()
    }

    fun accessDeniedHandler(): AccessDeniedHandler? {
        return SimpleAccessDeniedHandler()
    }

    /**
     * ログイン, ログアウト時のハンドリング
     */
    fun authenticationSuccessHandler(): AuthenticationSuccessHandler? {
        return SimpleAuthenticationSuccessHandler()
    }

    fun authenticationFailureHandler(): AuthenticationFailureHandler? {
        return SimpleAuthenticationFailureHandler()
    }

    fun logoutSuccessHandler(): LogoutSuccessHandler? {
        return HttpStatusReturningLogoutSuccessHandler()
    }
}
