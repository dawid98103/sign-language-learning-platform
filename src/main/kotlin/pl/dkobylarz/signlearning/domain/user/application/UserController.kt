package pl.dkobylarz.signlearning.domain.user.application

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.dkobylarz.signlearning.domain.authorization.domain.MessageResponse
import pl.dkobylarz.signlearning.domain.user.UserFacade
import pl.dkobylarz.signlearning.domain.user.domain.exception.UserNotFoundException

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
class UserController(private val userFacade: UserFacade)