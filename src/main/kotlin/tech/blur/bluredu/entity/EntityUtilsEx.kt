package tech.blur.bluredu.entity

import tech.blur.bluredu.domain.Company
import tech.blur.bluredu.domain.InternalUser
import tech.blur.bluredu.domain.User

fun UserEntity.toUser() = User(
        id,
        name,
        nickname,
        email,
        company?.toCompany()
)

fun UserEntity.toInternalUser() = InternalUser(
        id,
        name,
        nickname,
        email,
        company?.toCompany(),
        passwordHash,
        notificationToken
)

fun CompanyEntity.toCompany() = Company(id, name, description)