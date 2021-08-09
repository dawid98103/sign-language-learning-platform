package pl.dkobylarz.signlearning.domain.user.core

import pl.dkobylarz.signlearning.domain.user.core.model.command.AddUserCommand
import pl.dkobylarz.signlearning.domain.user.core.model.User
import pl.dkobylarz.signlearning.domain.user.core.model.UserService

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