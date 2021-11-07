package pl.dkobylarz.signlearning.domain.forum.domain

import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.forum.dto.*
import pl.dkobylarz.signlearning.domain.forum.exception.PostNotFoundException
import pl.dkobylarz.signlearning.domain.forum.infrastructure.ForumUserClient
import pl.dkobylarz.signlearning.domain.forum.infrastructure.PostLikeClient
import pl.dkobylarz.signlearning.domain.forum.infrastructure.PostRepository
import pl.dkobylarz.signlearning.domain.user.domain.User
import java.util.logging.Logger

@Service
class PostService(
    private val postRepository: PostRepository,
    private val forumUserClient: ForumUserClient,
    private val postLikeClient: PostLikeClient
) {

    companion object {
        val LOG = Logger.getLogger(PostService::class.java.name)
    }

    fun getPost(postId: Int, currentlyLoggedUser: User): PostDTO {
        val post: Post? = postRepository.findByPostId(postId)
        if (post != null) {
            val postAuthor = forumUserClient.getUserById(post.userId)
            val postLikes = postLikeClient.getLikesForPost(post.postId)
            val postDTO = ForumMapper.mapToPostDTO(post, postAuthor, currentlyLoggedUser, postLikes)
            postDTO.comments = getCommentsForPost(post, currentlyLoggedUser)
            return postDTO
        } else {
            throw PostNotFoundException()
        }
    }

    fun getSimplePosts(currentlyLoggedUser: User): Set<SimplePostDTO> {
        val posts: Set<Post> = postRepository.findAll()
        return posts.asSequence()
            .map {
                val postAuthor = forumUserClient.getUserById(it.userId)
                val postLikes = postLikeClient.getLikesForPost(it.postId)
                ForumMapper.mapToSimplePost(it, postAuthor, currentlyLoggedUser, postLikes)
            }
            .toSet()
    }

    private fun getCommentsForPost(post: Post, currentlyLoggedUser: User): Set<CommentDTO> {
        return post.comments.map {
            val userFromComment = forumUserClient.getUserById(it.userId)
            ForumMapper.mapToCommentDTO(it, userFromComment, isCurrentlyLoggedUserAnAuthor(currentlyLoggedUser, it))
        }.toSet()
    }

    fun createPost(post: CreatePostDTO, user: User) {
        LOG.info("[FORUM]: Utworzono post o temacie [${post.topic}] przez użytkownika z id: [${user.userId}]")
        val postToSave: Post = ForumMapper.mapToPost(post, user.userId!!)
        postRepository.save(postToSave)
    }

    fun updatePost(postWithNewContent: SimplePostDTO) {
        val post: Post? = postRepository.findByPostId(postWithNewContent.postId)
        post.let {
            val updatedComment = ForumMapper.mapToPost(postWithNewContent, post!!)
            postRepository.save(updatedComment)
        }
    }

    fun deletePost(postId: Int) {
        val post: Post? = postRepository.findByPostId(postId)
        if (post != null) {
            postRepository.delete(post)
        } else {
            throw PostNotFoundException()
        }
    }

    fun createComment(comment: CreateCommentDTO, user: User) {
        val post: Post? = postRepository.findByPostId(comment.postId)
        val commentToSave: Comment = ForumMapper.mapToComment(comment, user.userId!!)
        post.let {
            post!!.addComment(commentToSave)
            postRepository.save(post)
        }
    }

    fun deleteComment(postId: Int, commentId: Int) {
        val post: Post? = postRepository.findByPostId(postId)
        if (post !== null) {
            post.deleteComment(commentId)
            postRepository.save(post)
        } else {
            throw PostNotFoundException()
        }
    }

    fun updateComment(postId: Int, commentDTO: CommentDTO) {
        val post: Post? = postRepository.findByPostId(postId)
        if (post !== null) {
            val currentComment = post.comments.find { it.commentId == commentDTO.commentId }!!
            val updatedComment = ForumMapper.mapToComment(commentDTO, currentComment)
            post.deleteComment(currentComment.commentId)
            post.addComment(updatedComment)
            postRepository.save(post)
        } else {
            throw PostNotFoundException()
        }
    }

    private fun isCurrentlyLoggedUserAnAuthor(currentlyLoggedUser: User, comment: Comment): Boolean {
        return currentlyLoggedUser.userId == comment.userId
    }
}