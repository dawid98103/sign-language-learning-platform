package pl.dkobylarz.signlearning.domain.forum.domain

import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.forum.dto.CreateCommentDTO
import pl.dkobylarz.signlearning.domain.forum.dto.CreatePostDTO
import pl.dkobylarz.signlearning.domain.forum.dto.PostDTO
import pl.dkobylarz.signlearning.domain.forum.dto.SimplePostDTO
import pl.dkobylarz.signlearning.domain.forum.exception.PostNotFoundException
import pl.dkobylarz.signlearning.domain.forum.infrastructure.ForumUserClient
import pl.dkobylarz.signlearning.domain.forum.infrastructure.PostRepository
import java.util.logging.Logger

@Service
class PostService(private val postRepository: PostRepository, private val forumUserClient: ForumUserClient) {

    companion object {
        val LOG = Logger.getLogger(PostService::class.java.name)
    }

    fun createPost(post: CreatePostDTO, userId: Int) {
        LOG.info("[FORUM]: Utworzono post o temacie [${post.topic}] przez użytkownika z id: [${userId}]")
        val postToSave: Post = ForumMapper.mapToPost(post, userId)
        postRepository.save(postToSave)
    }

    fun createComment(comment: CreateCommentDTO, userId: Int) {
        val post: Post? = postRepository.findByPostId(comment.postId)
        val commentToSave: Comment = ForumMapper.mapToComment(comment, userId)
        post.let {
            post!!.addComment(commentToSave)
            postRepository.save(post)
        }
    }

    fun getPost(postId: Int): PostDTO {
        val post: Post? = postRepository.findByPostId(postId)
        if(post != null){
            return ForumMapper.mapToPostDTO(post, forumUserClient.getUserById(post.userId))
        }else {
            throw PostNotFoundException()
        }
    }

    fun getSimplePosts(): Set<SimplePostDTO> {
        val posts: Set<Post> = postRepository.findAll()
        return posts.asSequence()
            .map { ForumMapper.mapToSimplePost(it, forumUserClient.getUserById(it.userId)) }
            .toSet()
    }
}