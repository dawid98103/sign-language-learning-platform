package pl.dkobylarz.signlearning.domain.user.infrastructure

import pl.dkobylarz.signlearning.domain.user.domain.User
import pl.dkobylarz.signlearning.domain.user.domain.UserPlatform

interface UserDatabase {
    fun saveUser(user: User)
    fun getUserByUsername(username: String): User
    fun existsByUsername(username: String): Boolean
    fun getUserById(userId: Int): UserPlatform
}