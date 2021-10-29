package pl.dkobylarz.signlearning.domain.forum.infrastructure

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.user.UserFacade
import pl.dkobylarz.signlearning.domain.user.domain.UserPlatform

@Service
@RequiredArgsConstructor
class ForumUserClient(private val userFacade: UserFacade) {

    fun getUserById(userId: Int): UserPlatform {
        return userFacade.getUserById(userId)
    }
}