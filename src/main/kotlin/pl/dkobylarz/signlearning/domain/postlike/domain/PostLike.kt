package pl.dkobylarz.signlearning.domain.postlike.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("post_like")
data class PostLike(
    @Id
    val postLikeId: Int,
    val postId: Int,
    val userId: Int,
)
