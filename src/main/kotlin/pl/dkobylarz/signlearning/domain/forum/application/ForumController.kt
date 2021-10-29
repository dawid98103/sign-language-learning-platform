package pl.dkobylarz.signlearning.domain.forum.application

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import pl.dkobylarz.signlearning.domain.authorization.dto.MessageResponseDTO
import pl.dkobylarz.signlearning.domain.forum.ForumFacade
import pl.dkobylarz.signlearning.domain.forum.dto.CreateCommentDTO
import pl.dkobylarz.signlearning.domain.forum.dto.CreatePostDTO
import pl.dkobylarz.signlearning.domain.forum.dto.PostDTO
import pl.dkobylarz.signlearning.domain.forum.dto.SimplePostDTO
import pl.dkobylarz.signlearning.domain.user.domain.User

@RestController
@RequiredArgsConstructor
@RequestMapping("/forum")
class ForumController(private val forumFacade: ForumFacade) {

    @GetMapping("/post/{postId}")
    fun getPosts(@PathVariable postId: Int): ResponseEntity<PostDTO> {
        val post: PostDTO = forumFacade.getPost(postId)
        return ResponseEntity.ok(post)
    }

    @GetMapping("/simple/post")
    fun getSimplePosts(): ResponseEntity<Set<SimplePostDTO>> {
        val simplePosts: Set<SimplePostDTO> = forumFacade.getSimplePosts()
        return ResponseEntity.ok(simplePosts)
    }

    @PostMapping("/post")
    fun createPost(
        @RequestBody post: CreatePostDTO,
        @AuthenticationPrincipal user: User?
    ): ResponseEntity<MessageResponseDTO> {
        forumFacade.createPost(post, user?.userId!!)
        return ResponseEntity.ok(MessageResponseDTO("Pomyślnie utworzono post!"))
    }

    @PatchMapping("/post/comment")
    fun createComment(
        @RequestBody comment: CreateCommentDTO,
        @AuthenticationPrincipal user: User?
    ): ResponseEntity<MessageResponseDTO> {
        forumFacade.createComment(comment, user?.userId!!)
        return ResponseEntity.ok(MessageResponseDTO("Pomyślnie utworzono komentarz!"))
    }
}
