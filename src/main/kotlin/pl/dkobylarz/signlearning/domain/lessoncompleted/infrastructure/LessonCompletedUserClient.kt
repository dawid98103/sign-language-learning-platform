package pl.dkobylarz.signlearning.domain.lessoncompleted.infrastructure

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.user.UserFacade
import pl.dkobylarz.signlearning.domain.user.constant.PointsToGain

@Service
@RequiredArgsConstructor
class LessonCompletedUserClient(private val userFacade: UserFacade) {

    fun addPointsToAccount(userId: Int, pointsToGain: PointsToGain) {
        userFacade.assignPointsToAccount(userId, pointsToGain)
    }
}