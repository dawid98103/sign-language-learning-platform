package pl.dkobylarz.signlearning.domain.achievementuser.domain

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.achievement.domain.Achievement
import pl.dkobylarz.signlearning.domain.achievementuser.dto.AchievementUserDTO
import pl.dkobylarz.signlearning.domain.achievementuser.infrastructure.AchievementUserRepository
import java.time.LocalDateTime
import java.util.logging.Logger

@Service
@RequiredArgsConstructor
class AchievementUserService(private val achievementUserRepository: AchievementUserRepository) {

    companion object {
        val LOG = Logger.getLogger(AchievementUserService::class.java.name)
    }

    fun getAllByUserId(userId: Int): Set<AchievementUserDTO> {
        val userAchievements = achievementUserRepository.findByUserId(userId)
        return userAchievements.asSequence()
            .map { AchievementUserMapper.toDto(it) }
            .toSet()
    }

    fun save(achievement: Achievement, userId: Int) {
        LOG.info("[ACHIEVEMENTS]: Przyznano osiągnięcie [${achievement.name}] użytkownikowi o id [${userId}]")

        val achievementUserToSave = AchievementUser(
            0,
            achievement.achievementId,
            userId,
            LocalDateTime.now()
        )
        achievementUserRepository.save(achievementUserToSave)
    }
}