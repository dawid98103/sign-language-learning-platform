package pl.dkobylarz.signlearning.domain.achievement.application

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.dkobylarz.signlearning.domain.achievement.domain.AchievementFacade
import pl.dkobylarz.signlearning.domain.achievement.dto.AchievementDTO
import pl.dkobylarz.signlearning.domain.user.domain.User

@RestController
@RequestMapping("/achievement")
class AchievementController(private val achievementFacade: AchievementFacade) {

    @GetMapping("")
    fun getAchievement(@AuthenticationPrincipal user: User): ResponseEntity<Set<AchievementDTO>> {
        val userAchievements = achievementFacade.getAchievementsForUser(user)
        return ResponseEntity.ok(userAchievements)
    }

    @PostMapping("")
    fun calculateAchievement(@AuthenticationPrincipal user: User): ResponseEntity<Any> {
        achievementFacade.calculateAchievements(user)
        return ResponseEntity(HttpStatus.OK)
    }
}