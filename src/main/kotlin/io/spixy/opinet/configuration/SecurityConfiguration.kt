package io.spixy.opinet.configuration

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Scope
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
open class SecurityConfiguration : WebSecurityConfigurerAdapter() {
    private val logger = KotlinLogging.logger {}

    @Autowired
    lateinit var customAuthenticationProvider: CustomAuthenticationProvider

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(
            "/VAADIN/**",
            "/favicon.ico",
            "/robots.txt",
            "/manifest.webmanifest",
            "/sw.js",
            "/offline-page.html",
            "/frontend/**",
            "/webjars/**",
            "/frontend-es5/**", "/frontend-es6/**"
        )
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(customAuthenticationProvider)
    }

    @Bean
    @Scope("singleton")
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}