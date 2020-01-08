package tech.blur.bluredu.domain

data class User(
        val id: Int,
        val name: String,
        val nickname: String,
        val email: String,
        val company: Company?
)