package pl.dkobylarz.signlearning.domain.user.infrastructure

import pl.dkobylarz.signlearning.domain.user.core.model.User
import pl.dkobylarz.signlearning.domain.user.core.model.exception.UserNotFoundException

class UserDatabaseAdapter(private val userRepository: UserRepository) : UserDatabase {

    override fun saveUser(user: User) {
        userRepository.save(user)
    }

    override fun getUserByUsername(username: String): User {
        return userRepository.findUserPlatformByUsername(username)?.let { userPlatform ->
            User(
                userPlatform.id,
                userPlatform.username,
                userPlatform.password,
                userPlatform.name,
                userPlatform.surname,
                userPlatform.email,
                userPlatform.roleId
            )
        } ?: throw UserNotFoundException()
    }

    override fun existsByUsername(username: String): Boolean {
        return userRepository.existsByUsername(username)
    }
}