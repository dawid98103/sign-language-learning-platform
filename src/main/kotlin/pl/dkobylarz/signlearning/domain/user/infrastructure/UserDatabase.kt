package pl.dkobylarz.signlearning.domain.user.infrastructure

import pl.dkobylarz.signlearning.domain.user.domain.User

interface UserDatabase {
    fun saveUser(user: User)
    fun getUserByUsername(username: String): User
    fun existsByUsername(username: String): Boolean
}