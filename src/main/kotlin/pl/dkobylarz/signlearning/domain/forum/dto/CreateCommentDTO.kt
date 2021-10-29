package pl.dkobylarz.signlearning.domain.forum.dto

data class CreateCommentDTO(
    val content: String,
    val postId: Int
)
