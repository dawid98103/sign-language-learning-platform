package pl.dkobylarz.signlearning.domain.forum

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.dkobylarz.signlearning.domain.forum.domain.Post
import pl.dkobylarz.signlearning.domain.forum.domain.PostService
import pl.dkobylarz.signlearning.domain.forum.dto.*
import pl.dkobylarz.signlearning.domain.user.domain.User

@Service
@Transactional
class ForumFacade(private val postService: PostService) {

    fun createPost(post: CreatePostDTO, user: User) {
        postService.createPost(post, user)
    }

    fun getSimplePosts(user: User): Set<SimplePostDTO> {
        return postService.getSimplePosts(user)
    }
    fun getPostsForUser(user: User): Set<PostDTO> {
        return postService.getPostsForUser(user)
    }

    fun getPost(postId: Int, user: User): PostDTO {
        return postService.getPost(postId, user)
    }

    fun createComment(comment: CreateCommentDTO, user: User) {
        postService.createComment(comment, user)
    }

    fun deleteComment(postId: Int, commentId: Int) {
        postService.deleteComment(postId, commentId)
    }

    fun updateComment(postId: Int, commentDTO: CommentDTO) {
        postService.updateComment(postId, commentDTO)
    }

    fun deletePost(postId: Int) {
        postService.deletePost(postId)
    }

    fun updatePost(postWithNewContent: SimplePostDTO) {
        postService.updatePost(postWithNewContent)
    }
}