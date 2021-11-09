package pl.dkobylarz.signlearning.domain.user.domain

import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.user.domain.command.AddUserCommand
import pl.dkobylarz.signlearning.domain.user.exception.UserNotFoundException
import pl.dkobylarz.signlearning.domain.user.infrastructure.FriendRepository
import pl.dkobylarz.signlearning.domain.user.infrastructure.UserRepository
import java.time.LocalDateTime
import java.util.logging.Logger

@Service
class UserService(private val userRepository: UserRepository, private val friendRepository: FriendRepository) {

    companion object {
        val LOG = Logger.getLogger(UserService::class.java.name)
    }

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

        userRepository.save(user)
    }

    fun saveUser(user: User) {
        userRepository.save(user)
    }

    fun getUserByUsername(username: String): User {
        return userRepository.findByUsername(username).orElseGet { null }
    }

    fun getUserById(userId: Int): UserPlatform {
        return userRepository.findByUserId(userId)
    }

    fun existsByUsername(username: String): Boolean {
        return userRepository.existsByUsername(username)
    }

    fun addUserToFriends(username: String, user: User) {
        val userToAdd = userRepository.findUserPlatformByUsername(username)
        if (userToAdd != null) {
            val friend = Friend(
                0,
                user.userId!!,
                userToAdd.userId
            )
            friendRepository.save(friend)
        } else {
            throw UserNotFoundException()
        }
    }

    fun deleteUserFromFriends(username: String, user: User) {
        val userToDelete = userRepository.findUserPlatformByUsername(username)
        if(userToDelete != null){
            val friend = friendRepository.findByFirstUserIdAndSecondUserId(user.userId!!, userToDelete.userId)
            friendRepository.delete(friend)
        }else {
            throw UserNotFoundException()
        }
    }
}