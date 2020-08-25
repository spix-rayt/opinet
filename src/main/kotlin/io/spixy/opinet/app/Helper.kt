package io.spixy.opinet.app

import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

fun getAuth(): Authentication? {
    val authentication = SecurityContextHolder.getContext().authentication
    if(authentication == null || authentication is AnonymousAuthenticationToken) {
        return null
    }
    return authentication
}