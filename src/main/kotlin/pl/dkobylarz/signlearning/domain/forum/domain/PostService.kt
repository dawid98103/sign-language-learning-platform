package pl.dkobylarz.signlearning.domain.forum.domain

import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.forum.dto.*
import pl.dkobylarz.signlearning.domain.forum.exception.PostNotFoundException
import pl.dkobylarz.signlearning.domain.forum.infrastructure.ForumUserClient
import pl.dkobylarz.signlearning.domain.forum.infrastructure.PostRepository
import pl.dkobylarz.signlearning.domain.user.domain.User
import java.util.logging.Logger

@Service
class PostService(private val postRepository: PostRepository, private val forumUserClient: ForumUserClient) {

    companion object {
        val LOG = Logger.getLogger(PostService::class.java.name)
    }

    fun createPost(post: CreatePostDTO, user: User) {
        LOG.info("[FORUM]: Utworzono post o temacie [${post.topic}] przez użytkownika z id: [${user.userId}]")
        val postToSave: Post = ForumMapper.mapToPost(post, user.userId!!)
        postRepository.save(postToSave)
    }

    fun createComment(comment: CreateCommentDTO, user: User) {
        val post: Post? = postRepository.findByPostId(comment.postId)
        val commentToSave: Comment = ForumMapper.mapToComment(comment, user.userId!!)
        post.let {
            post!!.addComment(commentToSave)
            postRepository.save(post)
        }
    }

    fun getPost(postId: Int, user: User): PostDTO {
        val post: Post? = postRepository.findByPostId(postId)
        if (post != null) {
            val postDTO = ForumMapper.mapToPostDTO(post, forumUserClient.getUserById(post.userId))
            postDTO.comments = getCommentsForPost(post, user)
            return postDTO
        } else {
            throw PostNotFoundException()
        }
    }

    fun getSimplePosts(): Set<SimplePostDTO> {
        val posts: Set<Post> = postRepository.findAll()
        return posts.asSequence()
                .map { ForumMapper.mapToSimplePost(it, forumUserClient.getUserById(it.userId)) }
                .toSet()
    }

    private fun getCommentsForPost(post: Post, currentlyLoggedUser: User): Set<CommentDTO> {
        return post.comments.map {
            val userFromComment = forumUserClient.getUserById(it.userId)
            ForumMapper.mapToCommentDTO(it, userFromComment, isCurrentlyLoggedUserAnAuthor(currentlyLoggedUser, it))
        }.toSet()
    }

    private fun isCurrentlyLoggedUserAnAuthor(alreadyLoggedUser: User, comment: Comment): Boolean {
        return alreadyLoggedUser.userId == comment.userId
    }
}