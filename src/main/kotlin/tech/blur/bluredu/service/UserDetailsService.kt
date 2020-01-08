package tech.blur.bluredu.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tech.blur.bluredu.domain.InternalUser
import tech.blur.bluredu.domain.User
import tech.blur.bluredu.entity.toInternalUser
import tech.blur.bluredu.entity.toUser
import tech.blur.bluredu.repository.UserRepository

@Service("UserDetailsService")
class UserDetailsService @Autowired constructor(
        private val userRepository: UserRepository
) {
    fun getUserByNicname(nickname: String): User = userRepository.getUserEntityByNickname(nickname).toUser()

    fun getInternalUserByNicname(nickname: String): InternalUser = userRepository.getUserEntityByNickname(nickname).toInternalUser()
}