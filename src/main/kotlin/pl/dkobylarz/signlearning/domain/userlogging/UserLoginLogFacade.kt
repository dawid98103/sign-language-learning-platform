package pl.dkobylarz.signlearning.domain.userlogging

import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.userlogging.domain.UserLoginLogService
import pl.dkobylarz.signlearning.domain.userlogging.dto.UserLoginLogDTO

@Service
class UserLoginLogFacade(private val userLoginLogService: UserLoginLogService) {

    fun logUserLogin(userId: Int){
        userLoginLogService.logUserLogin(userId)
    }

    fun getLogsForUser(userId: Int): Set<UserLoginLogDTO> {
        return userLoginLogService.getLogsForUser(userId)
    }
}