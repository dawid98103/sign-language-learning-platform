package pl.dkobylarz.signlearning.domain.forum.infrastructure

import org.springframework.data.repository.CrudRepository
import pl.dkobylarz.signlearning.domain.forum.domain.Post

interface PostRepository : CrudRepository<Post, Int> {
    override fun findAll(): Set<Post>
    fun findByPostId(postId: Int): Post?
}