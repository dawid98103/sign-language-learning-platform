package pl.dkobylarz.signlearning.domain.authorization.application

import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import pl.dkobylarz.signlearning.domain.authorization.dto.*
import pl.dkobylarz.signlearning.domain.authorization.exception.PasswordsNotSameException
import pl.dkobylarz.signlearning.domain.authorization.exception.UsernameAlreadyExistsException
import pl.dkobylarz.signlearning.domain.user.UserFacade
import pl.dkobylarz.signlearning.domain.user.domain.User
import pl.dkobylarz.signlearning.domain.user.domain.UserRole
import pl.dkobylarz.signlearning.domain.userlogging.UserLoginLogFacade
import pl.dkobylarz.signlearning.infrastructure.security.JwtTokenUtils
import java.util.logging.Logger

@Slf4j
@RestController
@RequestMapping("/auth")
class AuthorizationController(
    private val authenticationManager: AuthenticationManager,
    private val userFacade: UserFacade,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenUtils: JwtTokenUtils,
    private val userLoginLogFacade: UserLoginLogFacade
) {

    companion object {
        val LOG = Logger.getLogger(AuthorizationController::class.java.name)
    }

    @PostMapping("/signin")
    fun authenticateUser(
        @RequestBody loginRequestDTO: LoginRequestDTO,
        @RequestHeader header: HttpHeaders
    ): ResponseEntity<JwtResponseDTO> {
        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(loginRequestDTO.username, loginRequestDTO.password)
        )

        LOG.info("Autoryzowano użytkownika: ${loginRequestDTO.username}")
        LOG.info("Host: ${header.getValue("host")}")
        LOG.info("User-agent: ${header.getValue("user-agent")}")

        SecurityContextHolder.getContext().authentication = authentication
        val jwt: String = jwtTokenUtils.generateJwtToken(authentication)

        val user: User = authentication.principal as User
        val roles: List<UserRole> = user.authorities.map { item -> UserRole.valueOf(item.authority) }

        userLoginLogFacade.logUserLogin(user.userId!!)

        return ResponseEntity.ok(
            JwtResponseDTO(
                jwt,
                user.userId,
                user.username,
                user.avatarUrl,
                user.email,
                roles
            )
        )
    }

    @PostMapping("/signup")
    fun registerUser(@RequestBody signupRequestDTO: SignupRequestDTO): ResponseEntity<MessageResponseDTO> {
        if (userFacade.existsByUsername(signupRequestDTO.username)) {
            throw UsernameAlreadyExistsException()
        }
        if(!userFacade.isSamePasswords(signupRequestDTO.password, signupRequestDTO.passwordRepeat)){
            throw PasswordsNotSameException()
        }

        val user = User(
            username = signupRequestDTO.username,
            password = passwordEncoder.encode(signupRequestDTO.password),
            name = signupRequestDTO.name,
            surname = signupRequestDTO.surname,
            email = signupRequestDTO.email,
            avatarUrl = signupRequestDTO.avatarUrl,
            active = true,
            roleId = 1

        )

        userFacade.saveUser(user)
        return ResponseEntity.ok(MessageResponseDTO("Pomyślnie zarejestrowano użytkownika"))
    }

    @PostMapping("/validateToken")
    fun validateToken(@RequestBody token: TokenValidationRequestDTO): ResponseEntity<Boolean> {
        return ResponseEntity.ok(jwtTokenUtils.validateJwtToken(token = token.token))
    }
}