package pl.dkobylarz.signlearning.domain.achievement.domain

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.dkobylarz.signlearning.domain.achievement.dto.AchievementDTO
import pl.dkobylarz.signlearning.domain.user.domain.User

@Service
@Transactional
@RequiredArgsConstructor
class AchievementFacade(private val achievementService: AchievementService) {

    fun calculateAchievements(user: User) {
        achievementService.calculateUnassignedAchievements(user)
    }

    fun getAchievementsForUser(user: User): Set<AchievementDTO> {
        return achievementService.getAchievementsForUser(user)
    }
}