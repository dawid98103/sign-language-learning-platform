package pl.dkobylarz.signlearning.domain.postlike.domain

import pl.dkobylarz.signlearning.domain.postlike.dto.PostLikeDTO

class PostLikeMapper {

    companion object{
        fun mapToPostLikeDTO(postLike: PostLike): PostLikeDTO{
            return PostLikeDTO(
                postLike.postLikeId,
                postLike.postId,
                postLike.userId
            )
        }
    }
}