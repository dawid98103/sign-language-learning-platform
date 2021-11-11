package pl.dkobylarz.signlearning.domain.user.application

import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import pl.dkobylarz.signlearning.domain.authorization.dto.MessageResponseDTO
import pl.dkobylarz.signlearning.domain.user.UserFacade
import pl.dkobylarz.signlearning.domain.user.domain.User
import pl.dkobylarz.signlearning.domain.user.domain.UserPlatform
import pl.dkobylarz.signlearning.domain.user.dto.UserBasicInfoDTO
import pl.dkobylarz.signlearning.domain.user.dto.UserBasicInfoWithFriendListDTO

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
class UserController(private val userFacade: UserFacade) {

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    fun getUsers(): ResponseEntity<Set<UserPlatform>>{
        return ResponseEntity.ok(userFacade.getUsers())
    }

    @DeleteMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteUserById(@PathVariable userId: Int): ResponseEntity<Any> {
        userFacade.deleteUserById(userId)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/user/info")
    fun getBasicInfo(@AuthenticationPrincipal user: User): ResponseEntity<UserBasicInfoDTO> {
        return ResponseEntity.ok(userFacade.getUserBasicInfo(user.userId!!))
    }

    @GetMapping("/user/info/{username}")
    fun getUserBasicInfo(@PathVariable username: String, @AuthenticationPrincipal currentLoggedUser: User): ResponseEntity<UserBasicInfoWithFriendListDTO> {
        return ResponseEntity.ok(userFacade.getUserBasicInfoWithFriendsList(username, currentLoggedUser))
    }

    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: Int): ResponseEntity<UserPlatform> {
        return ResponseEntity.ok(userFacade.getUserById(userId))
    }

    @PostMapping("/friend/{username}")
    fun addFriend(@PathVariable username: String, @AuthenticationPrincipal user: User): ResponseEntity<MessageResponseDTO>{
        userFacade.addUserToFriends(username, user);
        return ResponseEntity.ok(MessageResponseDTO("Pomyślnie dodano użytkownika do obserwowanych"))
    }

    @DeleteMapping("/friend/{username}")
    fun deleteFriend(@PathVariable username: String, @AuthenticationPrincipal user: User): ResponseEntity<MessageResponseDTO>{
        userFacade.deleteUserFromFriends(username, user);
        return ResponseEntity.ok(MessageResponseDTO("Pomyślnie usunięto użytkownika z obserwowanych"))
    }
}