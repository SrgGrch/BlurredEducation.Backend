package tech.blur.bluredu.service.authorities

import org.springframework.security.core.GrantedAuthority

class UserAuthority : GrantedAuthority {
    override fun getAuthority() = "ROLE_USER"

    companion object {
        const val authorityName = "ROLE_USER"
        const val roleName = "USER"
    }
}