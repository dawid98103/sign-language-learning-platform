package pl.dkobylarz.signlearning.domain.user.infrastructure

import pl.dkobylarz.signlearning.domain.user.core.model.User
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
}