package pl.dkobylarz.signlearning.domain.postlike.application

import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import pl.dkobylarz.signlearning.domain.postlike.PostLikeFacade
import pl.dkobylarz.signlearning.domain.user.domain.User

@RequiredArgsConstructor
@RequestMapping("/forum/post/like")
@RestController
class PostLikeController(val postLikeFacade: PostLikeFacade) {

    @PostMapping("/{postId}")
    fun likePost(@PathVariable postId: Int, @AuthenticationPrincipal user: User): ResponseEntity<Any> {
        postLikeFacade.addLikeToPost(postId, user)
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/{postId}")
    fun dislikePost(@PathVariable postId: Int, @AuthenticationPrincipal user: User): ResponseEntity<Any> {
        postLikeFacade.deleteLikeFromPost(postId, user)
        return ResponseEntity(HttpStatus.OK)
    }
}