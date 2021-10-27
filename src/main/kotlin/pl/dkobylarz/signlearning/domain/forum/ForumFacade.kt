package pl.dkobylarz.signlearning.domain.forum

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.dkobylarz.signlearning.domain.forum.domain.PostService
import pl.dkobylarz.signlearning.domain.forum.dto.CreatePostDTO

@Service
@Transactional
class ForumFacade(private val postService: PostService) {

    fun createPost(post: CreatePostDTO, userId: Int){
        postService.createPost(post, userId)
    }
}