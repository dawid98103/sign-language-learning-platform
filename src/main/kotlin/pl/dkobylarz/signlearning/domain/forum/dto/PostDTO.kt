package pl.dkobylarz.signlearning.domain.forum.dto

data class PostDTO(
    val simplePostDTO: SimplePostDTO,
    val comments: Set<CommentDTO>
)
