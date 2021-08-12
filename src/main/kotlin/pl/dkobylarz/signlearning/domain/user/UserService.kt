package pl.dkobylarz.signlearning.domain.user

import pl.dkobylarz.signlearning.domain.user.core.model.User
import pl.dkobylarz.signlearning.domain.user.core.model.command.AddUserCommand
import pl.dkobylarz.signlearning.domain.user.infrastructure.UserDatabase
import java.time.LocalDateTime

class UserService(private val userDatabase: UserDatabase) {

    fun saveUser(addUserCommand: AddUserCommand) {
        val user = User(
            username = addUserCommand.username,
            password = addUserCommand.password,
            name = addUserCommand.name,
            surname = addUserCommand.surname,
            email = addUserCommand.email,
            roleId = addUserCommand.roleId,
            avatarUrl = addUserCommand.avatarUrl,
            creationDate = LocalDateTime.now()
        )

        userDatabase.saveUser(user)
    }

    fun saveUser(user: User) {
        userDatabase.saveUser(user)
    }

    fun getUserByUsername(username: String): User {
        return userDatabase.getUserByUsername(username)
    }

    fun existsByUsername(username: String): Boolean {
        return userDatabase.existsByUsername(username)
    }
}