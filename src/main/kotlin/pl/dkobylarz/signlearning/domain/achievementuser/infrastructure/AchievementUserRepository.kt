package pl.dkobylarz.signlearning.domain.achievementuser.infrastructure

import org.springframework.data.repository.CrudRepository
import pl.dkobylarz.signlearning.domain.achievementuser.domain.AchievementUser

interface AchievementUserRepository : CrudRepository<AchievementUser, Int> {
    fun findByUserId(userId: Int): Set<AchievementUser>
}