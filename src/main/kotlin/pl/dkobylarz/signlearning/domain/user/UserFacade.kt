package pl.dkobylarz.signlearning.domain.user

import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.user.constant.PointsToGain
import pl.dkobylarz.signlearning.domain.user.domain.*
import pl.dkobylarz.signlearning.domain.user.dto.UserBasicInfoDTO

@Service
class UserFacade(
    private val userService: UserService,
    private val basicInfoService: BasicInfoService,
    private val userPointsService: UserPointsService
) {

    fun saveUser(user: User) {
        userService.saveUser(user)
    }

    fun getUserById(userId: Int): UserPlatform {
        return userService.getUserById(userId)
    }

    fun getUserByUsername(username: String): User {
        return userService.getUserByUsername(username)
    }

    fun getUserBasicInfo(userId: Int): UserBasicInfoDTO? {
        return basicInfoService.getUserBasicInfo(userId)
    }

    fun assignPointsToAccount(userId: Int, pointsToGain: PointsToGain) {
        userPointsService.addPointsToAccount(userId, pointsToGain)
    }

    fun existsByUsername(username: String): Boolean {
        return userService.existsByUsername(username)
    }

    fun isSamePasswords(password: String, repeatPassword: String): Boolean {
        return password == repeatPassword
    }
}