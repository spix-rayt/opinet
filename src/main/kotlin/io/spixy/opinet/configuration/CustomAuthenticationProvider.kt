package io.spixy.opinet.configuration

import io.spixy.opinet.app.DataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationProvider : AuthenticationProvider {
    @Autowired
    lateinit var dataService: DataService


    override fun authenticate(authentication: Authentication): Authentication? {
        val user = dataService.auth(authentication.principal.toString(), authentication.credentials.toString())
        if(user != null) {
            return UsernamePasswordAuthenticationToken(user.login, user.password, arrayListOf())
        } else {
            return null
        }
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }
}