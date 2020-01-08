package tech.blur.bluredu.api.models

import tech.blur.bluredu.domain.Company

data class RegUserRequest(
        val id: Int,
        val name: String,
        val nickname: String,
        val email: String,
        val password: String,
        val company: Company?
)