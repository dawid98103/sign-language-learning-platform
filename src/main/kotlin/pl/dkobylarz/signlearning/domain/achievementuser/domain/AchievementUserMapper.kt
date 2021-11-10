package pl.dkobylarz.signlearning.domain.achievementuser.domain

import pl.dkobylarz.signlearning.domain.achievementuser.dto.AchievementUserDTO

class AchievementUserMapper {

    companion object {
        fun toDto(achievementUser: AchievementUser): AchievementUserDTO {
            return AchievementUserDTO(
                achievementUser.achievementId,
                achievementUser.userId,
                achievementUser.creationDate
            )
        }
    }
}