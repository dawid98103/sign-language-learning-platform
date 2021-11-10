package pl.dkobylarz.signlearning.domain.achievementuser.domain

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.dkobylarz.signlearning.domain.achievement.domain.Achievement
import pl.dkobylarz.signlearning.domain.achievementuser.dto.AchievementUserDTO

@Service
@Transactional
@RequiredArgsConstructor
class AchievementUserFacade(private val achievementUserService: AchievementUserService) {

    fun getAllByUserId(userId: Int): Set<AchievementUserDTO> {
        return achievementUserService.getAllByUserId(userId)
    }

    fun save(achievement: Achievement, userId: Int) {
        achievementUserService.save(achievement, userId)
    }
}