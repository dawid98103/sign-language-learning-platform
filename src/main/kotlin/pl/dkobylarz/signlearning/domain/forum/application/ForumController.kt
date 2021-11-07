package pl.dkobylarz.signlearning.domain.forum.application

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import pl.dkobylarz.signlearning.domain.authorization.dto.MessageResponseDTO
import pl.dkobylarz.signlearning.domain.forum.ForumFacade
import pl.dkobylarz.signlearning.domain.forum.dto.*
import pl.dkobylarz.signlearning.domain.user.domain.User

@RestController
@RequiredArgsConstructor
@RequestMapping("/forum")
class ForumController(private val forumFacade: ForumFacade) {

    @GetMapping("/post/{postId}")
    fun getPosts(
        @PathVariable postId: Int,
        @AuthenticationPrincipal user: User
    ): ResponseEntity<PostDTO> {
        val post: PostDTO = forumFacade.getPost(postId, user)
        return ResponseEntity.ok(post)
    }

    @DeleteMapping("/post/{postId}")
    fun deletePost(
        @PathVariable postId: Int
    ): ResponseEntity<MessageResponseDTO> {
        forumFacade.deletePost(postId)
        return ResponseEntity.ok(MessageResponseDTO("Pomyślnie usunięto post!"))
    }

    @PutMapping("/post")
    fun updatePost(
        @RequestBody postWithNewContent: SimplePostDTO
    ): ResponseEntity<MessageResponseDTO> {
        forumFacade.updatePost(postWithNewContent)
        return ResponseEntity.ok(MessageResponseDTO("Pomyślnie zaktualizowano post!"))
    }

    @GetMapping("/simple/post")
    fun getSimplePosts(@AuthenticationPrincipal user: User): ResponseEntity<Set<SimplePostDTO>> {
        val simplePosts: Set<SimplePostDTO> = forumFacade.getSimplePosts(user)
        return ResponseEntity.ok(simplePosts)
    }

    @PostMapping("/post")
    fun createPost(
        @RequestBody post: CreatePostDTO,
        @AuthenticationPrincipal user: User
    ): ResponseEntity<MessageResponseDTO> {
        forumFacade.createPost(post, user)
        return ResponseEntity.ok(MessageResponseDTO("Pomyślnie utworzono post!"))
    }

    @DeleteMapping("/post/{postId}/comment/{commentId}")
    fun deleteComment(
        @PathVariable postId: Int,
        @PathVariable commentId: Int
    ): ResponseEntity<MessageResponseDTO> {
        forumFacade.deleteComment(postId, commentId)
        return ResponseEntity.ok(MessageResponseDTO("Pomyślnie usunięto komentarz!"))
    }

    @PutMapping("/post/{postId}/comment")
    fun updateComment(
        @PathVariable postId: Int,
        @RequestBody commentDTO: CommentDTO
    ): ResponseEntity<MessageResponseDTO> {
        forumFacade.updateComment(postId, commentDTO);
        return ResponseEntity.ok(MessageResponseDTO("Pomyślnie zaktualizowano komentarz!"))
    }

    @PatchMapping("/post/{postId}/comment")
    fun createComment(
        @PathVariable postId: Int,
        @RequestBody comment: CreateCommentDTO,
        @AuthenticationPrincipal user: User
    ): ResponseEntity<MessageResponseDTO> {
        forumFacade.createComment(comment, user)
        return ResponseEntity.ok(MessageResponseDTO("Pomyślnie utworzono komentarz!"))
    }
}
