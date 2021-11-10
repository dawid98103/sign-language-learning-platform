package pl.dkobylarz.signlearning.domain.achievement.domain

import pl.dkobylarz.signlearning.domain.achievement.dto.AchievementDTO
import java.time.LocalDateTime

class AchievementMapper {

    companion object {
        fun toDto(achievement: Achievement, creationDate: LocalDateTime? = null): AchievementDTO {
            return AchievementDTO(
                achievement.localeName,
                creationDate
            )
        }
    }
}