package pl.dkobylarz.signlearning.domain.lessoncompleted.infrastructure

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.user.UserFacade

@Service
@RequiredArgsConstructor
class LessonCompletedUserClient(private val userFacade: UserFacade) {

    fun addPointsToAccount(userId: Int, points: Int) {
        userFacade.assignPointsToAccount(userId, points)
    }
}