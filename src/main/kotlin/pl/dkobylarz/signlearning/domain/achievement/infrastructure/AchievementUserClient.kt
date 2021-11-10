package pl.dkobylarz.signlearning.domain.achievement.infrastructure

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.achievement.domain.Achievement
import pl.dkobylarz.signlearning.domain.achievementuser.domain.AchievementUser
import pl.dkobylarz.signlearning.domain.achievementuser.domain.AchievementUserFacade
import pl.dkobylarz.signlearning.domain.achievementuser.dto.AchievementUserDTO

@Service
@RequiredArgsConstructor
class AchievementUserClient(private val achievementUserFacade: AchievementUserFacade) {

    fun getAllByUserId(userId: Int): Set<AchievementUserDTO>{
        return achievementUserFacade.getAllByUserId(userId)
    }

    fun save(achievement: Achievement, userId: Int) {
        achievementUserFacade.save(achievement, userId)
    }
}