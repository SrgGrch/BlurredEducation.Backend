package tech.blur.bluredu.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import tech.blur.bluredu.domain.InternalUser
import tech.blur.bluredu.domain.User
import tech.blur.bluredu.entity.toInternalUser
import tech.blur.bluredu.entity.toUser
import tech.blur.bluredu.repository.UserRepository
import org.springframework.security.core.userdetails.User as SpringUser

@Service("UserDetailsService")
class UserService @Autowired constructor(
        private val userRepository: UserRepository
) : UserDetailsService {
    fun getUserByNickname(nickname: String): User = userRepository.findUserEntityByNickname(nickname).toUser()

    fun getInternalUserByNickname(nickname: String): InternalUser = userRepository.findUserEntityByNickname(nickname).toInternalUser()

    override fun loadUserByUsername(username: String?): UserDetails {
        val internalUser = username?.let {
            userRepository.findUserEntityByNickname(it).toInternalUser()
        } ?: throw IllegalStateException("Username must not be null")

        return SpringUser(internalUser.nickname, internalUser.passwordHash, null)
    }
}