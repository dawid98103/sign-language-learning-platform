package pl.dkobylarz.signlearning.domain.achievementuser.dto

import java.time.LocalDateTime

data class AchievementUserDTO(
    val achievementId: Int,
    val userId: Int,
    val creationDateTime: LocalDateTime
)
