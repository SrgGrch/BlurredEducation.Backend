package tech.blur.bluredu.domain

data class InternalUser(
        val id: Int,
        val name: String,
        val nickname: String,
        val email: String,
        val company: Company?,
        val passwordHash: String,
        val notificationToken: String?
)