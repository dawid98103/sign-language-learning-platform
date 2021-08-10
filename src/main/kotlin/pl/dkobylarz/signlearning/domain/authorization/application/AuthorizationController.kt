package pl.dkobylarz.signlearning.domain.authorization.application

import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import pl.dkobylarz.signlearning.domain.authorization.core.model.JwtResponse
import pl.dkobylarz.signlearning.domain.authorization.core.model.MessageResponse
import pl.dkobylarz.signlearning.domain.authorization.core.model.command.LoginCommand
import pl.dkobylarz.signlearning.domain.authorization.core.model.command.SignupCommand
import pl.dkobylarz.signlearning.domain.user.core.UserFacade
import pl.dkobylarz.signlearning.domain.user.core.model.User
import pl.dkobylarz.signlearning.infrastructure.security.JwtTokenUtils
import java.time.LocalDateTime

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
        var roles: List<String> = user.authorities.map { item -> item.authority }

        return ResponseEntity.ok(
            JwtResponse(
                jwt,
                user.id,
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
            email = signupCommand.email,
            avatarUrl = signupCommand.avatarUrl,
            roleId = 1
        )

        userFacade.saveUser(user)
        return ResponseEntity.ok(MessageResponse("Pomyślnie zarejestrowano użytkownika"))
    }
}