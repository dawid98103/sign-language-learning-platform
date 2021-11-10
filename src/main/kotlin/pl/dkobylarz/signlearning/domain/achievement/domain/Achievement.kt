package pl.dkobylarz.signlearning.domain.achievement.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("achievement")
data class Achievement(
    @Id
    val achievementId: Int,
    val name: String,
    val localeName: String,
    val description: String
)

