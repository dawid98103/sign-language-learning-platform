package pl.dkobylarz.signlearning.domain.achievement.infrastructure

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.forum.ForumFacade
import pl.dkobylarz.signlearning.domain.forum.dto.PostDTO
import pl.dkobylarz.signlearning.domain.user.domain.User

@Service
@RequiredArgsConstructor
class ForumClient(private val forumFacade: ForumFacade) {

    fun getUserPosts(user: User): Set<PostDTO> {
        return forumFacade.getPostsForUser(user)
    }
}