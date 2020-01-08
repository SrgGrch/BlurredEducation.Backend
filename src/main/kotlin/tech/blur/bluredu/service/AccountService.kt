package tech.blur.bluredu.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tech.blur.bluredu.api.models.AuthResponse

@Service("Account")
class AccountService @Autowired constructor(
        val tokenService: TokenService,
        val userDetailsService: UserDetailsService
) {
    fun getToken(nickname: String?, password: String?): AuthResponse? {
        val token = tokenService.getToken(nickname, password)

        return if (token != null && nickname != null)
            AuthResponse(token, userDetailsService.getUserByNicname(nickname))
        else
            null
    }
}