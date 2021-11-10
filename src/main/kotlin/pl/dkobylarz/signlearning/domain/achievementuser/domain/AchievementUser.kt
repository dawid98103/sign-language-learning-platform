package pl.dkobylarz.signlearning.domain.achievementuser.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("achievement_user")
data class AchievementUser(
    @Id
    val achievementUserId: Int,
    val achievementId: Int,
    val userId: Int,
    val creationDate: LocalDateTime
)
