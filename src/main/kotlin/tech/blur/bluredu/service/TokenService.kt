package tech.blur.bluredu.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tech.blur.bluredu.common.Result
import tech.blur.bluredu.errors.BlurError
import tech.blur.bluredu.errors.NoSuchUserError
import tech.blur.bluredu.errors.WrongCredentialsError
import tech.blur.bluredu.model.TokenObject
import java.util.*


@Deprecated("Use spring security")
@Service("TokenService")
class TokenService @Autowired constructor(
        private val userService: UserService
) {
    fun getToken(username: String, password: String): Result<TokenObject, BlurError> {
        val tokenData = HashMap<String, Any>()

        val user = try {
            userService.getInternalUserByNickname(username)
        } catch (e: NoSuchUserError) {
            return Result.failure(NoSuchUserError())
        }

        return if (password == user.passwordHash) {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.YEAR, 100)

            tokenData.run {
                put("clientType", "user")
                put("userID", user.id.toString())
                put("username", user.nickname)
                put("token_create_date", Date().time)
                put("token_expiration_date", calendar.time)
            }

            val jwtBuilder = Jwts.builder()
            jwtBuilder.setExpiration(calendar.time)
            jwtBuilder.setClaims(tokenData)

            val keyBytes = Decoders.BASE64.decode("RDY1QWRTSjc0NTFLNms2ajU2NGE2RDQ5Szg0Sjk4NG42NWE0NnNkOGlqbkFTTmtkam5hanNkbTg1c2FkNTZzYWQ0YXM2ZDRhM3M1ZDQ5NGY0cjQ1NmFzNGQ=")
            val key = Keys.hmacShaKeyFor(keyBytes)
            val token = TokenObject(jwtBuilder.signWith(key, SignatureAlgorithm.HS512).compact(), calendar.timeInMillis)

            userService.saveTokenToDatabase(user.id, token.token)

            Result.success(token)
        } else {
            Result.failure(WrongCredentialsError())
        }
    }
}