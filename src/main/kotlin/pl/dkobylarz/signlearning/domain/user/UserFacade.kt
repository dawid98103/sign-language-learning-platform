package pl.dkobylarz.signlearning.domain.user

import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.user.domain.BasicInfoService
import pl.dkobylarz.signlearning.domain.user.domain.User
import pl.dkobylarz.signlearning.domain.user.domain.UserPlatform
import pl.dkobylarz.signlearning.domain.user.domain.UserService
import pl.dkobylarz.signlearning.domain.user.dto.UserBasicInfoDTO

@Service
class UserFacade(private val userService: UserService, private val basicInfoService: BasicInfoService) {

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

    fun existsByUsername(username: String): Boolean {
        return userService.existsByUsername(username)
    }

    fun isSamePasswords(password: String, repeatPassword: String): Boolean {
        return password == repeatPassword
    }
}