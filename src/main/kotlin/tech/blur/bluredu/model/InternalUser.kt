package tech.blur.bluredu.model

open class InternalUser(
        id: Int,
        name: String,
        nickname: String,
        email: String,
        company: Company?,
        val passwordHash: String,
        val notificationToken: String?
) : User(id, name, nickname, email, company) {
    constructor(internalUser: InternalUser) : this(
            internalUser.id,
            internalUser.name,
            internalUser.nickname,
            internalUser.email,
            internalUser.company,
            internalUser.passwordHash,
            internalUser.notificationToken
    )
}