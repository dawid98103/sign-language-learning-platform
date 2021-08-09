package pl.dkobylarz.signlearning.domain.user.application

import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.dkobylarz.signlearning.domain.user.core.UserFacade

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
class UserController(private val userFacade: UserFacade) {

}