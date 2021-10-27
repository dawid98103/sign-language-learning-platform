package pl.dkobylarz.signlearning.domain.forum.domain

import pl.dkobylarz.signlearning.domain.forum.dto.CreatePostDTO
import java.time.LocalDateTime

class ForumMapper {

    companion object {
        fun mapToPost(postToCreate: CreatePostDTO, userId: Int): Post {
            return Post(
                    0,
                    postToCreate.topic,
                    postToCreate.content,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    userId,
                    emptySet()
            )
        }
    }
}