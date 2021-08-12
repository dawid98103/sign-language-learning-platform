package pl.dkobylarz.signlearning.domain.user

import pl.dkobylarz.signlearning.domain.user.core.model.User
import pl.dkobylarz.signlearning.domain.user.core.model.command.AddUserCommand

class UserFacade(private val userService: UserService) {

    fun saveUser(user: User){
        userService.saveUser(user)
    }

    fun saveUserCommand(addUserCommand: AddUserCommand) {
        userService.saveUser(addUserCommand)
    }

    fun getUserByUsername(username: String): User {
        return userService.getUserByUsername(username)
    }

    fun existsByUsername(username: String): Boolean{
        return userService.existsByUsername(username)
    }
}