package pl.dkobylarz.signlearning.domain.user.infrastructure

import pl.dkobylarz.signlearning.domain.user.domain.User
import pl.dkobylarz.signlearning.domain.user.domain.UserPlatform
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class UserDatabaseInMemoryAdapter : UserDatabase {

    lateinit var userDatabase: ConcurrentHashMap<Int, User>

    override fun saveUser(user: User) {
        userDatabase[userDatabase.size + 1] = user
    }

    override fun getUserByUsername(username: String): User {
        return userDatabase.asSequence().filter { (_, value) -> value.username == username }.first().value
    }

    override fun existsByUsername(username: String): Boolean {
        return userDatabase.asSequence().filter { (_, value) -> value.username == username }.firstOrNull() != null
    }

    override fun getUserById(userId: Int): UserPlatform {
        val user: User = Optional.ofNullable(userDatabase[userId]).orElseThrow()
        return UserPlatform(
            userId = user.userId!!,
            username = user.username,
            password = user.password,
            name = user.name!!,
            surname = user.surname!!,
            email = user.email!!,
            roleId = user.roleId!!,
            points = user.points!!,
            active = user.active!!,
            avatarUrl = user.avatarUrl!!,
            creationDate = user.creationDate!!
        )
    }
}