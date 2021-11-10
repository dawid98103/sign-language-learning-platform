package pl.dkobylarz.signlearning.domain.achievement.infrastructure

import org.springframework.data.repository.CrudRepository
import pl.dkobylarz.signlearning.domain.achievement.domain.Achievement

interface AchievementRepository : CrudRepository<Achievement, Int> {
    override fun findAll(): Set<Achievement>
}