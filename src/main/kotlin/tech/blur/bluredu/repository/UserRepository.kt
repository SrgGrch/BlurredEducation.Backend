package tech.blur.bluredu.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import tech.blur.bluredu.entity.UserEntity

@Repository("UserRepository")
interface UserRepository : JpaRepository<UserEntity, String> {
    fun getUserEntityByNickname(nickname: String): UserEntity
}