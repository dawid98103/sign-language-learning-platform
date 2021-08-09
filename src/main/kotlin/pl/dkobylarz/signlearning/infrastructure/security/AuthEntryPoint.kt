package pl.dkobylarz.signlearning.infrastructure.security

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthEntryPoint: AuthenticationEntryPoint {

    override fun commence(request: HttpServletRequest?, response: HttpServletResponse?, exception: AuthenticationException?) {
        response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "ERROR: Unauthorized")
    }
}