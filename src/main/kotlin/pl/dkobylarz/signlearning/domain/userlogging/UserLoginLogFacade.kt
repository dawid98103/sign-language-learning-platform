package pl.dkobylarz.signlearning.domain.userlogging

import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.userlogging.domain.UserLoginLogService

@Service
class UserLoginLogFacade(private val userLoginLogService: UserLoginLogService) {

    fun logUserLogin(userId: Int){
        userLoginLogService.logUserLogin(userId)
    }
}