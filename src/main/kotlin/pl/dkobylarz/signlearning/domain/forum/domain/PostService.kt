package pl.dkobylarz.signlearning.domain.forum.domain

import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.forum.dto.CreatePostDTO
import pl.dkobylarz.signlearning.domain.forum.infrastructure.PostRepository
import pl.dkobylarz.signlearning.domain.quizprocess.domain.QuizProcessService
import java.util.logging.Logger

@Service
class PostService(private val postRepository: PostRepository) {

    companion object {
        val LOG = Logger.getLogger(QuizProcessService::class.java.name)
    }

    fun createPost(post: CreatePostDTO, userId: Int) {
        LOG.info("[FORUM]: Utworzono post o temacie [${post.topic}] przez użytkownika z id: [${userId}]")
        val postToSave: Post = ForumMapper.mapToPost(post, userId)
        postRepository.save(postToSave)
    }
}