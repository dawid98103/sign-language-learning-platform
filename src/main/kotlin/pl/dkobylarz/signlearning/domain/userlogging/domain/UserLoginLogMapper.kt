package pl.dkobylarz.signlearning.domain.userlogging.domain

import pl.dkobylarz.signlearning.domain.userlogging.dto.UserLoginLogDTO

class UserLoginLogMapper {

    companion object {

        fun toDTO(userLoginLog: UserLoginLog): UserLoginLogDTO {
            return UserLoginLogDTO(
                userLoginLog.userId,
                userLoginLog.loginTimestamp
            )
        }
    }
}