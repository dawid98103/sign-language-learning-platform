package pl.dkobylarz.signlearning.domain.forum

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.dkobylarz.signlearning.domain.forum.domain.PostService
import pl.dkobylarz.signlearning.domain.forum.dto.CreateCommentDTO
import pl.dkobylarz.signlearning.domain.forum.dto.CreatePostDTO
import pl.dkobylarz.signlearning.domain.forum.dto.PostDTO
import pl.dkobylarz.signlearning.domain.forum.dto.SimplePostDTO
import pl.dkobylarz.signlearning.domain.user.domain.User

@Service
@Transactional
class ForumFacade(private val postService: PostService) {

    fun createPost(post: CreatePostDTO, user: User) {
        postService.createPost(post, user)
    }

    fun getSimplePosts(): Set<SimplePostDTO> {
        return postService.getSimplePosts()
    }

    fun getPost(postId: Int, user: User): PostDTO {
        return postService.getPost(postId, user)
    }

    fun createComment(comment: CreateCommentDTO, user: User) {
        postService.createComment(comment, user)
    }
}