package pl.dkobylarz.signlearning.domain.postlike.dto

data class PostLikeDTO(
    val postLikeId: Int,
    val postId: Int,
    val userId: Int
)
