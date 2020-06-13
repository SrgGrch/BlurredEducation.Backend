package tech.blur.bluredu.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tech.blur.bluredu.api.models.AuthResponse
import tech.blur.bluredu.api.models.RegisterRequest
import tech.blur.bluredu.common.Result
import tech.blur.bluredu.common.mapSuccess
import tech.blur.bluredu.errors.BlurError
import tech.blur.bluredu.errors.NicknameAlreadyTaken
import tech.blur.bluredu.errors.NoSuchUserError
import tech.blur.bluredu.model.InternalUser
import tech.blur.bluredu.model.User
import tech.blur.bluredu.repository.entity.UserEntity
import tech.blur.bluredu.repository.entity.toUser

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

    fun getUserEntityByToken(token: String): Result<UserEntity, NoSuchUserError> {
        return userService.getUserEntityByToken(token)?.let {
            Result.success<UserEntity, NoSuchUserError>(it)
        } ?: Result.failure(NoSuchUserError())
    }

    fun getUserByToken(token: String): Result<User, NoSuchUserError> {
        val res = userService.getUserByToken(token)?.let {
            Result.success<User, NoSuchUserError>(it)
        } ?: Result.failure(NoSuchUserError())

        return res
    }

    fun getInternalUserByToken(token: String): Result<InternalUser, NoSuchUserError> {
        return userService.getInternalUserByToken(token)?.let {
            Result.success<InternalUser, NoSuchUserError>(it)
        } ?: Result.failure(NoSuchUserError())
    }

    fun registerUser(registerRequest: RegisterRequest): Result<AuthResponse, BlurError> {
        if (userService.isNicknameTaken(registerRequest.nickname)) {
            return Result.failure(NicknameAlreadyTaken())
        }

        val user = userService.registerUser(registerRequest)
        val token = tokenService.getToken(user.nickname, registerRequest.password)
        if (token is Result.Success)
            return Result.success(
                    AuthResponse(token.value, user.toUser())
            )
        else {
            return Result.failure(NicknameAlreadyTaken())
        }
    }

    fun isTokenValid(token: String): Boolean = getUserByToken(token).isSuccess
}