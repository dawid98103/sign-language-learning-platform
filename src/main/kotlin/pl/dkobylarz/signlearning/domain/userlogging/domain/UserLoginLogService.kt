package pl.dkobylarz.signlearning.domain.userlogging.domain

import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.userlogging.dto.UserLoginLogDTO
import pl.dkobylarz.signlearning.domain.userlogging.infrastructure.UserLoginLogRepository
import java.time.LocalDateTime

@Service
class UserLoginLogService(private val userLoginLogRepository: UserLoginLogRepository) {

    fun logUserLogin(userId: Int) {
        userLoginLogRepository.save(
            UserLoginLog(
                0,
                userId,
                LocalDateTime.now()
            )
        )
    }

    fun getLogsForUser(userId: Int): Set<UserLoginLogDTO> {
        val userLoginLogs = userLoginLogRepository.findByUserId(userId)
        return userLoginLogs.asSequence()
            .map { UserLoginLogMapper.toDTO(it) }
            .toSet()
    }
}