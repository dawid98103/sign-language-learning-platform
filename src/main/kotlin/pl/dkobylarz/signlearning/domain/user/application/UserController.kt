package pl.dkobylarz.signlearning.domain.user.application

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.dkobylarz.signlearning.domain.user.UserFacade
import pl.dkobylarz.signlearning.domain.user.domain.User
import pl.dkobylarz.signlearning.domain.user.domain.UserPlatform
import pl.dkobylarz.signlearning.domain.user.dto.UserBasicInfoDTO

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
class UserController(private val userFacade: UserFacade) {

    @GetMapping("/user/info")
    fun getBasicInfo(@AuthenticationPrincipal user: User): ResponseEntity<UserBasicInfoDTO> {
        return ResponseEntity.ok(userFacade.getUserBasicInfo(user.userId!!))
    }

    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: Int): ResponseEntity<UserPlatform> {
        return ResponseEntity.ok(userFacade.getUserById(userId))
    }
}