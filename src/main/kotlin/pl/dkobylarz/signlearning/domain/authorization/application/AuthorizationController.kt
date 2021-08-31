package pl.dkobylarz.signlearning.domain.authorization.application

import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import pl.dkobylarz.signlearning.domain.authorization.domain.JwtResponse
import pl.dkobylarz.signlearning.domain.authorization.domain.MessageResponse
import pl.dkobylarz.signlearning.domain.authorization.domain.command.LoginCommand
import pl.dkobylarz.signlearning.domain.authorization.domain.command.SignupCommand
import pl.dkobylarz.signlearning.domain.authorization.domain.command.TokenValidationCommand
import pl.dkobylarz.signlearning.domain.user.UserFacade
import pl.dkobylarz.signlearning.domain.user.domain.User
import pl.dkobylarz.signlearning.domain.user.domain.UserRole
import pl.dkobylarz.signlearning.infrastructure.security.JwtTokenUtils

@RestController
@RequestMapping("/auth")
class AuthorizationController(
    private val authenticationManager: AuthenticationManager,
    private val userFacade: UserFacade,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenUtils: JwtTokenUtils
) {

    @PostMapping("/signin")
    fun authenticateUser(@RequestBody loginCommand: LoginCommand): ResponseEntity<JwtResponse> {
        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(loginCommand.username, loginCommand.password)
        )

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
    fun registerUser(@RequestBody signupCommand: SignupCommand): ResponseEntity<MessageResponse> {
        if (userFacade.existsByUsername(signupCommand.username)) {
            return ResponseEntity.badRequest().body(MessageResponse("Nazwa użytkownika zajęta!"))
        }

        val user = User(
            username = signupCommand.username,
            password = passwordEncoder.encode(signupCommand.password),
            name = signupCommand.name,
            surname = signupCommand.surname,
            email = signupCommand.email,
            avatarUrl = signupCommand.avatarUrl,
            active = true,
            roleId = 1
        )

        userFacade.saveUser(user)
        return ResponseEntity.ok(MessageResponse("Pomyślnie zarejestrowano użytkownika"))
    }

    @PostMapping("/validateToken")
    fun validateToken(@RequestBody token: TokenValidationCommand): ResponseEntity<Boolean>{
        return ResponseEntity.ok(jwtTokenUtils.validateJwtToken(token = token.token))
    }
}