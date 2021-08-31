package pl.dkobylarz.signlearning.domain.user.domain

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import pl.dkobylarz.signlearning.domain.lesson.domain.Lesson
import pl.dkobylarz.signlearning.domain.lesson.domain.LessonStageCompleted
import java.time.LocalDateTime
import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.Size
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

@Table("platform_user")
class User(
    @Id
    var userId: Int? = null,
    private val username: String = "",
    private val password: String = "",
    val name: String? = "",
    val surname: String? = "",
    @Email
    val email: String? = "",
    val roleId: Int? = null,
    val points: Int? = 0,
    val active: Boolean? = false,
    @Size(max = 2083, message = "url should have max 2083 chars!")
    val avatarUrl: String? = "",
    val creationDate: LocalDateTime? = LocalDateTime.now(),
    private val authorities: MutableCollection<out GrantedAuthority> = Collections.emptyList()
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authoritiesList: MutableCollection<GrantedAuthority> = ArrayList()
        when (this.roleId) {
            1 -> authoritiesList.add(SimpleGrantedAuthority(UserRole.ROLE_USER.roleName))
            2 -> {
                authoritiesList.add(SimpleGrantedAuthority(UserRole.ROLE_USER.roleName))
                authoritiesList.add(SimpleGrantedAuthority(UserRole.ROLE_ADMIN.roleName))
            }
        }

        return authoritiesList
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
