package tech.blur.bluredu.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import tech.blur.bluredu.entity.UserEntity
import javax.transaction.Transactional

@Repository("UserRepository")
interface UserRepository : JpaRepository<UserEntity, Int> {
    fun findUserEntityByNickname(nickname: String): UserEntity

    @Modifying
    @Query("UPDATE UserEntity set token = ?2 where id = ?1")
    @Transactional
    fun updateTokenById(id: Int, token: String)

    fun getUserEntityByToken(token: String): UserEntity?
}