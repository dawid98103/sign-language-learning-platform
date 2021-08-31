package pl.dkobylarz.signlearning.domain.user

import pl.dkobylarz.signlearning.domain.user.domain.User
import pl.dkobylarz.signlearning.domain.user.domain.UserPlatform
import pl.dkobylarz.signlearning.domain.user.domain.UserService

class UserFacade(private val userService: UserService) {

    fun saveUser(user: User) {
        userService.saveUser(user)
    }

    fun getUserById(userId: Int): UserPlatform {
        return userService.getUserById(userId)
    }

    fun getUserByUsername(username: String): User {
        return userService.getUserByUsername(username)
    }

    fun existsByUsername(username: String): Boolean {
        return userService.existsByUsername(username)
    }
}