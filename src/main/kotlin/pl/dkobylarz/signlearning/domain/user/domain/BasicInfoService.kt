package pl.dkobylarz.signlearning.domain.user.domain

import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.user.dto.UserBasicInfoDTO
import pl.dkobylarz.signlearning.domain.user.dto.UserBasicInfoWithFriendListDTO
import pl.dkobylarz.signlearning.domain.user.exception.UserNotFoundException
import pl.dkobylarz.signlearning.domain.user.infrastructure.FriendRepository
import pl.dkobylarz.signlearning.domain.user.infrastructure.UserLoggingClient
import pl.dkobylarz.signlearning.domain.user.infrastructure.UserRepository
import pl.dkobylarz.signlearning.domain.userlogging.dto.UserLoginLogDTO
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class BasicInfoService(
    private val userRepository: UserRepository,
    private val friendRepository: FriendRepository,
    private val userLoggingClient: UserLoggingClient
) {

    fun getUserBasicInfo(userId: Int): UserBasicInfoDTO? {
        val userLoginLogs: Set<UserLoginLogDTO> = userLoggingClient.getLoginLogsForUser(userId)
        val user: UserPlatform = userRepository.findByUserId(userId)
        val lastLogged: LocalDateTime = getLastLoggedDate(userLoginLogs)
        val learningDaysInRow: Int = calculateConsecutiveLearningDays(userId, userLoginLogs)
        val gainedPoints = user.points

        return UserBasicInfoDTO(
            user.username,
            user.name,
            user.surname,
            user.avatarUrl,
            user.creationDate,
            lastLogged,
            learningDaysInRow,
            gainedPoints,
            user.roleId
        )
    }

    fun getUserBasicInfoWithFriendsList(username: String, currentLoggedUser: User): UserBasicInfoWithFriendListDTO? {
        val user: UserPlatform? = userRepository.findUserPlatformByUsername(username)
        if (user != null) {
            val userLoginLogs: Set<UserLoginLogDTO> = userLoggingClient.getLoginLogsForUser(user.userId)
            val learningDaysInRow: Int = calculateConsecutiveLearningDays(user.userId, userLoginLogs)
            val lastLogged: LocalDateTime = getLastLoggedDate(userLoginLogs)
            val gainedPoints = user.points
            val userFriends = getFriendsForUser(user.userId)
            val isCurrentLoggedUser = isCurrentLoggedUser(user, currentLoggedUser)

            return UserMapper.mapToUserBasicInfoWithFriendsList(
                user,
                lastLogged,
                learningDaysInRow,
                gainedPoints,
                userFriends,
                isCurrentLoggedUser
            )
        } else {
            throw UserNotFoundException()
        }
    }

    fun calculateConsecutiveLearningDays(userId: Int, userLoginLogs: Set<UserLoginLogDTO> = emptySet()): Int {
        val baseUserLoginLogSet = userLoginLogs.ifEmpty { userLoggingClient.getLoginLogsForUser(userId) }

        var daysCount = 0
        val currentDate = LocalDate.now()
        val userLoginDates = baseUserLoginLogSet.asSequence()
            .map { it.loginDateTime.toLocalDate() }
            .distinct()
            .sortedDescending()
            .toList()

        if (userLoginDates.size < 2) {
            return 0
        } else {
            if (userLoginDates[0].equals(currentDate)) {
                for (i in baseUserLoginLogSet.indices) {
                    if (i == baseUserLoginLogSet.size - 1) {
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
            return if (daysCount > 0) {
                daysCount + 1
            } else {
                daysCount
            }
        }
    }

    private fun isCurrentLoggedUser(user: UserPlatform, currentLoggedUser: User): Boolean {
        return user.userId == currentLoggedUser.userId
    }

    private fun getFriendsForUser(userId: Int): Set<UserBasicInfoDTO> {
        val userLoginLogs: Set<UserLoginLogDTO> = userLoggingClient.getLoginLogsForUser(userId)
        val lastLogged: LocalDateTime = getLastLoggedDate(userLoginLogs)
        val learningDaysInRow: Int = calculateConsecutiveLearningDays(userId, userLoginLogs)

        val friends = friendRepository.findByFirstUserId(userId)
        return friends.asSequence()
            .map { userRepository.findByUserId(it.secondUserId) }
            .map { UserMapper.mapToUserBasicInfo(it, lastLogged, learningDaysInRow, it.points) }
            .toSet()
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
}