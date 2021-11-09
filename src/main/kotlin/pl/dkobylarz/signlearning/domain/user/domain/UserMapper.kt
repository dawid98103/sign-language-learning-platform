package pl.dkobylarz.signlearning.domain.user.domain

import pl.dkobylarz.signlearning.domain.user.dto.UserBasicInfoDTO
import pl.dkobylarz.signlearning.domain.user.dto.UserBasicInfoWithFriendListDTO
import java.time.LocalDateTime

class UserMapper {

    companion object {
        fun mapToUserBasicInfo(
            user: UserPlatform,
            lastLogged: LocalDateTime,
            learningDaysInRow: Int,
            gainedPoints: Int
        ): UserBasicInfoDTO {
            return UserBasicInfoDTO(
                user.username,
                user.name,
                user.surname,
                user.avatarUrl,
                user.creationDate,
                lastLogged,
                learningDaysInRow,
                gainedPoints
            )
        }

        fun mapToUserBasicInfoWithFriendsList(
            user: UserPlatform,
            lastLogged: LocalDateTime,
            learningDaysInRow: Int,
            gainedPoints: Int,
            friends: Set<UserBasicInfoDTO>,
            isCurrentLoggedUser: Boolean
        ): UserBasicInfoWithFriendListDTO {
            val userBasicInfo = mapToUserBasicInfo(user, lastLogged, learningDaysInRow, gainedPoints)
            return UserBasicInfoWithFriendListDTO(
                userBasicInfo,
                friends,
                isCurrentLoggedUser
            )
        }
    }
}