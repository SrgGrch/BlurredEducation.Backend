package tech.blur.bluredu.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import tech.blur.bluredu.entity.toInternalUser
import tech.blur.bluredu.entity.toUser
import tech.blur.bluredu.errors.NoSuchUserError
import tech.blur.bluredu.model.InternalUser
import tech.blur.bluredu.model.User
import tech.blur.bluredu.repository.UserRepository
import tech.blur.bluredu.service.authorities.UserAuthority
import org.springframework.security.core.userdetails.User as SpringUser

@Service("UserDetailsService")
class UserService @Autowired constructor(
        private val userRepository: UserRepository
) : UserDetailsService {
    fun getUserByNickname(nickname: String): User = userRepository.findUserEntityByNickname(nickname).toUser()
    fun getInternalUserByNickname(nickname: String): InternalUser = userRepository.findUserEntityByNickname(nickname).toInternalUser()

    fun getUserByToken(token: String) = userRepository.getUserEntityByToken(token)?.toUser()
    fun getUserEntityByToken(token: String) = userRepository.getUserEntityByToken(token)
    fun getInternalUserByToken(token: String) = userRepository.getUserEntityByToken(token)?.toInternalUser()

    override fun loadUserByUsername(username: String?): UserDetails {
        val internalUser = username?.let {
            userRepository.findUserEntityByNickname(it).toInternalUser()
        } ?: throw NoSuchUserError()

        return SpringUser(internalUser.nickname, internalUser.passwordHash, listOf(UserAuthority()))
    }

    fun saveTokenToDatabase(id: Int, token: String) {
        userRepository.updateTokenById(id, token)
    }
}