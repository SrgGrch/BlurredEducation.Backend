package tech.blur.bluredu.api

import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import tech.blur.bluredu.api.models.AuthRequest
import tech.blur.bluredu.api.models.AuthResponse
import tech.blur.bluredu.api.models.RegisterRequest
import tech.blur.bluredu.common.Result
import tech.blur.bluredu.core.BaseResponseEntity
import tech.blur.bluredu.service.AccountService

@Api(tags = ["Account"])
@RestController("AccountController")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class AccountController @Autowired constructor(
        private val accountService: AccountService
) {

    @ResponseBody
    @PostMapping("$ACCOUNT_ROOT/Login")
    fun login(@RequestBody authUserRequest: AuthRequest): BaseResponseEntity<AuthResponse> {
        return when (val response = accountService.getToken(authUserRequest.nickname, authUserRequest.password)) {
            is Result.Success -> {
                BaseResponseEntity(response.value)
            }
            is Result.Failure -> {
                BaseResponseEntity(null, HttpStatus.BAD_REQUEST, "Wrong credentials")
            }
        }
    }

    @ResponseBody
    @PostMapping("$ACCOUNT_ROOT/Register")
    fun register(@RequestBody registerRequest: RegisterRequest): BaseResponseEntity<AuthResponse> {
        return when (val res = accountService.registerUser(registerRequest)) {
            is Result.Success -> BaseResponseEntity(res.value)
            is Result.Failure -> BaseResponseEntity(null, HttpStatus.BAD_REQUEST)
        }
    }

    companion object {
        const val ACCOUNT_ROOT = "Account"
    }
}