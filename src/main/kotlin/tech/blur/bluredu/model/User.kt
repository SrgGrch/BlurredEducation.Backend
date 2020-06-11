package tech.blur.bluredu.model

open class User(
        val id: Int,
        val name: String,
        val nickname: String,
        val email: String,
        val company: Company?
) {
    constructor(user: User) : this(
            user.id,
            user.name,
            user.nickname,
            user.email,
            user.company
    )

    constructor(user: InternalUser) : this(
            user.id,
            user.name,
            user.nickname,
            user.email,
            user.company
    )
}