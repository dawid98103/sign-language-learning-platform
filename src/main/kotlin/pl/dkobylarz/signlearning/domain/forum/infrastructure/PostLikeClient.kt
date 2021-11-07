package pl.dkobylarz.signlearning.domain.forum.infrastructure

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.postlike.PostLikeFacade
import pl.dkobylarz.signlearning.domain.postlike.dto.PostLikeDTO

@Service
@RequiredArgsConstructor
class PostLikeClient(val postLikeFacade: PostLikeFacade) {

    fun getLikesForPost(postId: Int): Set<PostLikeDTO> {
        return postLikeFacade.getLikesForPost(postId)
    }
}