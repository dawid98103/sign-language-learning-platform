package pl.dkobylarz.signlearning.domain.postlike.domain

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.postlike.dto.PostLikeDTO
import pl.dkobylarz.signlearning.domain.postlike.exception.PostLikeNotFoundException
import pl.dkobylarz.signlearning.domain.postlike.infrastructure.PostLikeRepository
import pl.dkobylarz.signlearning.domain.user.domain.User

@Service
@RequiredArgsConstructor
class PostLikeService(val postLikeRepository: PostLikeRepository) {
    fun getLikesForPost(postId: Int): Set<PostLikeDTO> {
        val likesForPost = postLikeRepository.findByPostId(postId)
        return likesForPost.asSequence()
            .map { PostLikeMapper.mapToPostLikeDTO(it) }
            .toSet()
    }

    fun addLikeToPost(postId: Int, user: User) {
        if (postLikeRepository.existsByPostIdAndUserId(postId, user.userId!!)) {
            throw PostLikeNotFoundException()
        } else {
            val postLikeToSave = PostLike(0, postId, user.userId!!)
            postLikeRepository.save(postLikeToSave)
        }
    }

    fun deleteLikeFromPost(postId: Int, user: User) {
        if (postLikeRepository.existsByPostIdAndUserId(postId, user.userId!!)) {
            val postLikeToDelete = postLikeRepository.findByPostIdAndUserId(postId, user.userId!!)
            postLikeRepository.delete(postLikeToDelete)
        } else {
            throw PostLikeNotFoundException()
        }
    }
}