package pl.dkobylarz.signlearning.domain.user.infrastructure

import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.userlogging.UserLoginLogFacade
import pl.dkobylarz.signlearning.domain.userlogging.dto.UserLoginLogDTO

@Service
class UserLoggingClient(private val userLoginLogFacade: UserLoginLogFacade) {

    fun getLoginLogsForUser(userId: Int): Set<UserLoginLogDTO> {
        return userLoginLogFacade.getLogsForUser(userId)
    }
}