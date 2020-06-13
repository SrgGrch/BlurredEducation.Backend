package tech.blur.bluredu.repository.entity

import tech.blur.bluredu.model.Company
import tech.blur.bluredu.model.InternalUser
import tech.blur.bluredu.model.User

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