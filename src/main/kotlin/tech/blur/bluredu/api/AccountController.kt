package tech.blur.bluredu.api

import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import tech.blur.bluredu.api.models.AuthRequest
import tech.blur.bluredu.api.models.AuthResponse
import tech.blur.bluredu.core.BaseResponseEntity
import tech.blur.bluredu.service.AccountService

@Api(tags = ["Account"])
@Controller("AccountController")
class AccountController @Autowired constructor(
        private val accountService: AccountService
) {

    @PostMapping("$ACCOUNT_ROOT/Login")
    fun login(@RequestBody authUserRequest: AuthRequest): BaseResponseEntity<AuthResponse> {
        val response = accountService.getToken(authUserRequest.nickname, authUserRequest.password)

        return if (response != null) {
            BaseResponseEntity(response)
        } else {
            BaseResponseEntity(null, HttpStatus.BAD_REQUEST, "Wrong credentials")
        }
    }

    companion object {
        private const val ACCOUNT_ROOT = "Account"
    }
}