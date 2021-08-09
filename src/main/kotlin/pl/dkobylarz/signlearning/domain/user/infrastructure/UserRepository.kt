package pl.dkobylarz.signlearning.domain.user.infrastructure

import org.springframework.data.repository.CrudRepository
import pl.dkobylarz.signlearning.domain.user.core.model.User
import java.util.*

interface UserRepository : CrudRepository<User, Int> {
    fun existsByUsername(username: String): Boolean
    fun findByUsername(username: String): Optional<User>
}