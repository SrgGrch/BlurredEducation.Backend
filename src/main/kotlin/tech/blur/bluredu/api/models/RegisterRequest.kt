package tech.blur.bluredu.api.models

data class RegisterRequest(
        val nickname: String,
        val password: String,
        val email: String,
        val name: String
)