package pl.dkobylarz.signlearning.domain.postlike.infrastructure

import org.springframework.data.repository.CrudRepository
import pl.dkobylarz.signlearning.domain.postlike.domain.PostLike

interface PostLikeRepository : CrudRepository<PostLike, Int>{

    fun findByPostId(postId: Int): Set<PostLike>
    fun existsByPostIdAndUserId(postId: Int, userId: Int): Boolean
    fun findByPostIdAndUserId(postId: Int, userId: Int): PostLike
}