package tech.blur.bluredu.api.models

import tech.blur.bluredu.model.TokenObject
import tech.blur.bluredu.model.User

data class AuthResponse(
        val token: TokenObject,
        val userInfo: User
)