package tech.blur.bluredu.domain

data class TokenObject(
        val token: String,
        val expiresIn: Long
)