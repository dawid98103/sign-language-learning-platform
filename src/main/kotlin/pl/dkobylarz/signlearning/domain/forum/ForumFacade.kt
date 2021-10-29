package pl.dkobylarz.signlearning.domain.forum

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.dkobylarz.signlearning.domain.forum.domain.PostService
import pl.dkobylarz.signlearning.domain.forum.dto.CreateCommentDTO
import pl.dkobylarz.signlearning.domain.forum.dto.CreatePostDTO
import pl.dkobylarz.signlearning.domain.forum.dto.PostDTO
import pl.dkobylarz.signlearning.domain.forum.dto.SimplePostDTO

@Service
@Transactional
class ForumFacade(private val postService: PostService) {

    fun createPost(post: CreatePostDTO, userId: Int) {
        postService.createPost(post, userId)
    }

    fun getSimplePosts(): Set<SimplePostDTO> {
        return postService.getSimplePosts()
    }

    fun getPost(postId: Int): PostDTO {
        return postService.getPost(postId)
    }

    fun createComment(comment: CreateCommentDTO, userId: Int) {
        postService.createComment(comment, userId)
    }
}