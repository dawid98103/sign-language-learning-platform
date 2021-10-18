package pl.dkobylarz.signlearning.domain.user.infrastructure

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import pl.dkobylarz.signlearning.domain.user.domain.User
import pl.dkobylarz.signlearning.domain.user.domain.UserPlatform
import java.util.*

interface UserRepository : CrudRepository<User, Int> {

    fun existsByUsername(username: String): Boolean

    fun findByUsername(username: String): Optional<User>

    fun findByUserId(@Param("userId") user: Int): UserPlatform

    @Query("SELECT * FROM platform_user u WHERE u.username = :username")
    fun findUserPlatformByUsername(@Param("username") username: String): UserPlatform?

//    @Query("SELECT * FROM platform_user u WHERE u.username = :username")
//    fun findByUsername(@Param("username") username: String): Optional<User>
}