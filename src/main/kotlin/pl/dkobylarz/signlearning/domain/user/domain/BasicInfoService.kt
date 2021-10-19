package pl.dkobylarz.signlearning.domain.user.domain

import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.user.dto.UserBasicInfoDTO
import pl.dkobylarz.signlearning.domain.user.infrastructure.UserLoggingClient
import pl.dkobylarz.signlearning.domain.user.infrastructure.UserRepository
import pl.dkobylarz.signlearning.domain.userlogging.dto.UserLoginLogDTO
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class BasicInfoService(private val userRepository: UserRepository, private val userLoggingClient: UserLoggingClient) {

    fun getUserBasicInfo(userId: Int): UserBasicInfoDTO? {
        val userLoginLogs: Set<UserLoginLogDTO> = userLoggingClient.getLoginLogsForUser(userId)
        val user: UserPlatform = userRepository.findByUserId(userId)
        val lastLogged: LocalDateTime = getLastLoggedDate(userLoginLogs)
        val learningDaysInRow: Int = calculateConsecutiveLearningDays(userLoginLogs)
        val gainedPoints = user.points

        return UserBasicInfoDTO(
            lastLogged,
            learningDaysInRow,
            gainedPoints
        )
    }

    private fun getLastLoggedDate(userLoginLogs: Set<UserLoginLogDTO>): LocalDateTime {
        val userLoginDateTimes = userLoginLogs.asSequence()
            .map { it.loginDateTime }
            .distinct()
            .sortedDescending()
            .toList()

        return if (userLoginDateTimes.size > 1) {
            userLoginDateTimes[1]
        } else {
            LocalDateTime.now()
        }
    }

    private fun calculateConsecutiveLearningDays(userLoginLogs: Set<UserLoginLogDTO>): Int {
        var daysCount = 0
        val currentDate = LocalDate.now()
        val userLoginDates = userLoginLogs.asSequence()
            .map { it.loginDateTime.toLocalDate() }
            .distinct()
            .sortedDescending()
            .toList()

        if (userLoginDates[0].equals(currentDate)) {
            for (i in userLoginLogs.indices) {
                if (i == userLoginLogs.size - 1) {
                    break
                } else {
                    if (userLoginDates[i].minusDays(1).equals(userLoginDates[i + 1])) {
                        daysCount += 1
                    } else {
                        break
                    }
                }
            }
        }

        return daysCount
    }
}