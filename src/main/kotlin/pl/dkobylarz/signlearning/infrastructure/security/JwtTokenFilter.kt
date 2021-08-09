package pl.dkobylarz.signlearning.infrastructure.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import pl.dkobylarz.signlearning.domain.user.core.UserFacade
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class JwtTokenFilter(private val jwtTokenUtils: JwtTokenUtils, private val userFacade: UserFacade) :
    OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filter: FilterChain) {
        try {
            val token: String? = getTokenFromRequestHeader(request)
            if (token != null && jwtTokenUtils.validateJwtToken(token)) {
                val username: String = jwtTokenUtils.getUsernameFromJwtToken(token)
                val userDetails: UserDetails = userFacade.getUserByUsername(username)
                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: Exception) {
            println(e.localizedMessage)
        }

        filter.doFilter(request, response)
    }

    private fun getTokenFromRequestHeader(request: HttpServletRequest): String? {
        val authHeader: String = request.getHeader("Authorization")

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer")) {
            return authHeader.substring(7, authHeader.length)
        }

        return null!!
    }
}