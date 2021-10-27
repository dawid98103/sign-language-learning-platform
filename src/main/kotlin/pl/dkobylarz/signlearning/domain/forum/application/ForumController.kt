package pl.dkobylarz.signlearning.domain.forum.application

import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import pl.dkobylarz.signlearning.domain.authorization.dto.MessageResponseDTO
import pl.dkobylarz.signlearning.domain.forum.ForumFacade
import pl.dkobylarz.signlearning.domain.forum.dto.CreatePostDTO
import pl.dkobylarz.signlearning.domain.user.domain.User

@RestController("/forum")
class ForumController(private val forumFacade: ForumFacade) {

    @PostMapping("/post")
    fun createPost(@RequestBody post: CreatePostDTO, @AuthenticationPrincipal user: User?): ResponseEntity<MessageResponseDTO> {
        forumFacade.createPost(post, user?.userId!!)
        return ResponseEntity.ok(MessageResponseDTO("Pomyślnie utworzono post!"))
    }
}
