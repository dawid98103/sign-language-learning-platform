package pl.dkobylarz.signlearning.domain.postlike

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.dkobylarz.signlearning.domain.postlike.domain.PostLikeService
import pl.dkobylarz.signlearning.domain.postlike.dto.PostLikeDTO
import pl.dkobylarz.signlearning.domain.user.domain.User

@Service
@Transactional
@RequiredArgsConstructor
class PostLikeFacade(val postLikeService: PostLikeService) {

    fun getLikesForPost(postId: Int): Set<PostLikeDTO> {
        return postLikeService.getLikesForPost(postId)
    }

    fun addLikeToPost(postId: Int, user: User) {
        postLikeService.addLikeToPost(postId, user)
    }

    fun deleteLikeFromPost(postId: Int, user: User) {
        postLikeService.deleteLikeFromPost(postId, user)
    }
}