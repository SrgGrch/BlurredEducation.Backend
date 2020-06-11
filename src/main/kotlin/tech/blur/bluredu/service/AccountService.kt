package tech.blur.bluredu.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tech.blur.bluredu.api.models.AuthResponse
import tech.blur.bluredu.common.Result
import tech.blur.bluredu.common.mapSuccess
import tech.blur.bluredu.errors.BlurError
import tech.blur.bluredu.errors.NoSuchUserError
import tech.blur.bluredu.model.InternalUser
import tech.blur.bluredu.model.User

@Service("Account")
class AccountService @Autowired constructor(
        private val tokenService: TokenService,
        private val userService: UserService
) {
    fun getToken(nickname: String?, password: String?): Result<AuthResponse, BlurError> {
        if (nickname == null || password == null) return Result.failure(NoSuchUserError())

        return tokenService.getToken(nickname, password).mapSuccess {
            AuthResponse(it, userService.getUserByNickname(nickname))
        }
    }

    fun getUserByToken(token: String): Result<User, NoSuchUserError> {
        return userService.getUserByToken(token)?.let {
            Result.success<User, NoSuchUserError>(it)
        } ?: Result.failure(NoSuchUserError())
    }

    fun getInternalUserByToken(token: String): Result<InternalUser, NoSuchUserError> {
        return userService.getInternalUserByToken(token)?.let {
            Result.success<InternalUser, NoSuchUserError>(it)
        } ?: Result.failure(NoSuchUserError())
    }

    fun isTokenValid(token: String): Boolean = getUserByToken(token) is Result.Success
}