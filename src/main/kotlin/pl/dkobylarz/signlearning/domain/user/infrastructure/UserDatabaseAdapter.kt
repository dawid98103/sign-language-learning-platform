package pl.dkobylarz.signlearning.domain.user.infrastructure

import pl.dkobylarz.signlearning.domain.user.core.model.User

class UserDatabaseAdapter(private val userRepository: UserRepository) : UserDatabase {

    override fun saveUser(user: User) {
        userRepository.save(user)
    }

    override fun getUserByUsername(username: String): User {
        return userRepository.findByUsername(username).orElseThrow()
    }

    override fun existsByUsername(username: String): Boolean {
        return userRepository.existsByUsername(username)
    }
}