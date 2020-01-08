package tech.blur.bluredu.api.models

import tech.blur.bluredu.domain.TokenObject
import tech.blur.bluredu.domain.User

data class AuthResponse(
        val token: TokenObject,
        val userInfo: User
)