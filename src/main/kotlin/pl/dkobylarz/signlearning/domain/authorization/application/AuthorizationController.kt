package pl.dkobylarz.signlearning.domain.authorization.application

import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import pl.dkobylarz.signlearning.domain.authorization.dto.*
import pl.dkobylarz.signlearning.domain.user.UserFacade
import pl.dkobylarz.signlearning.domain.user.domain.User
import pl.dkobylarz.signlearning.domain.user.domain.UserRole
import pl.dkobylarz.signlearning.infrastructure.security.JwtTokenUtils

@Slf4j
@RestController
@RequestMapping("/auth")
class AuthorizationController(
    private val authenticationManager: AuthenticationManager,
    private val userFacade: UserFacade,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenUtils: JwtTokenUtils
) {

    fun <T> loggerFor(clazz: Class<T>) = LoggerFactory.getLogger(clazz)
    private val logger = loggerFor(this::class.java)

    @PostMapping("/signin")
    fun authenticateUser(
        @RequestBody loginRequestDto: LoginRequestDto,
        @RequestHeader header: HttpHeaders
    ): ResponseEntity<JwtResponse> {
        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(loginRequestDto.username, loginRequestDto.password)
        )

        logger.info("Autoryzowano użytkownika: ${loginRequestDto.username}")
        logger.info("Host: ${header.getValue("host")}")
        logger.info("User-agent: ${header.getValue("user-agent")}")

        SecurityContextHolder.getContext().authentication = authentication
        val jwt: String = jwtTokenUtils.generateJwtToken(authentication)

        val user: User = authentication.principal as User
        val roles: List<UserRole> = user.authorities.map { item -> UserRole.valueOf(item.authority) }

        return ResponseEntity.ok(
            JwtResponse(
                jwt,
                user.userId,
                user.username,
                user.email,
                roles
            )
        )
    }

    @PostMapping("/signup")
    fun registerUser(@RequestBody signupRequestDto: SignupRequestDto): ResponseEntity<MessageResponse> {
        if (userFacade.existsByUsername(signupRequestDto.username)) {
            return ResponseEntity.badRequest().body(MessageResponse("Nazwa użytkownika zajęta!"))
        }

        val user = User(
            username = signupRequestDto.username,
            password = passwordEncoder.encode(signupRequestDto.password),
            name = signupRequestDto.name,
            surname = signupRequestDto.surname,
            email = signupRequestDto.email,
            avatarUrl = signupRequestDto.avatarUrl,
            active = true,
            roleId = 1

        )

        userFacade.saveUser(user)
        return ResponseEntity.ok(MessageResponse("Pomyślnie zarejestrowano użytkownika"))
    }

    @PostMapping("/validateToken")
    fun validateToken(@RequestBody token: TokenValidationRequestDto): ResponseEntity<Boolean> {
        return ResponseEntity.ok(jwtTokenUtils.validateJwtToken(token = token.token))
    }
}