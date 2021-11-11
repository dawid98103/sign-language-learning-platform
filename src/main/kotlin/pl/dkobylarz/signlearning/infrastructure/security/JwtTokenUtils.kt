package pl.dkobylarz.signlearning.infrastructure.security

import io.jsonwebtoken.*
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.user.domain.User
import java.util.*

@Service
class JwtTokenUtils {

    val secretKey: String = "zstlD3JK81m6wTTgsNFhqzwqP"

    fun generateJwtToken(authentication: Authentication): String {
        val userPrincipal: User = authentication.principal as User

        return Jwts.builder()
            .setSubject(userPrincipal.username)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .claim("userId", userPrincipal.userId)
            .claim("roles", userPrincipal.roleId)
            .compact()
    }

    fun getUsernameFromJwtToken(token: String): String {
        val claims: Claims = Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .body

        return claims.subject
    }

    fun validateJwtToken(token: String): Boolean {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            return true
        } catch (e: SignatureException) {
            println(e.localizedMessage)
        } catch (e: MalformedJwtException) {
            println(e.localizedMessage)
        } catch (e: ExpiredJwtException) {
            println(e.localizedMessage)
        } catch (e: UnsupportedJwtException) {
            println(e.localizedMessage)
        } catch (e: IllegalArgumentException) {
            println(e.localizedMessage)
        }
        return false
    }
}