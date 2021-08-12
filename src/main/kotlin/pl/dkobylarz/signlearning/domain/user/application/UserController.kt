package pl.dkobylarz.signlearning.domain.user.application

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.dkobylarz.signlearning.domain.authorization.core.model.MessageResponse
import pl.dkobylarz.signlearning.domain.user.UserFacade
import pl.dkobylarz.signlearning.domain.user.core.model.exception.UserNotFoundException

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
class UserController(private val userFacade: UserFacade) {

    @GetMapping("/test")
    @PreAuthorize("hasRole('USER')")
    fun test(): ResponseEntity<MessageResponse> {
        return ResponseEntity.ok(MessageResponse("OK"))
    }

    @GetMapping("/test2")
    @PreAuthorize("hasRole('ADMIN')")
    fun test2(): ResponseEntity<MessageResponse> {
        return ResponseEntity.ok(MessageResponse("OK"))
    }

    @GetMapping("/test3")
    fun test3(): ResponseEntity<MessageResponse> {
        throw UserNotFoundException()
        return ResponseEntity.ok(MessageResponse("Znaleziono użytkownika"))
    }
}