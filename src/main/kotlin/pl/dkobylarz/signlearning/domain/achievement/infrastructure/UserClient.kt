package pl.dkobylarz.signlearning.domain.achievement.infrastructure

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.user.UserFacade
import pl.dkobylarz.signlearning.domain.user.domain.Friend

@Service
@RequiredArgsConstructor
class UserClient(private val userFacade: UserFacade) {

    fun getUserFriends(userId: Int): Set<Friend> {
        return userFacade.getUserFriends(userId)
    }

    fun getConsecutiveLearningDays(userId: Int): Int {
        return userFacade.getConsecutiveLearningDaysForUser(userId)
    }
}