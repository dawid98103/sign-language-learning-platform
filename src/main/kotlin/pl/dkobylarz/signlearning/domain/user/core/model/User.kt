package pl.dkobylarz.signlearning.domain.user.core.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import pl.dkobylarz.signlearning.domain.user.core.model.UserRole
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

@Table("platform_user")
class User(
    @Id
    var id: Int? = null,
    private val username: String = "",
    private val password: String = "",
    var name: String? = "",
    var surname: String? = "",
    var email: String? = "",
    var roleId: Int? = null,
    var points: Int? = 0,
    var active: Boolean? = false,
    var avatarUrl: String? = "",
    var creationDate: LocalDateTime? = LocalDateTime.now(),
    @Transient
    private val authorities: MutableCollection<out GrantedAuthority> = Collections.emptyList()
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authoritiesList: MutableCollection<GrantedAuthority> = ArrayList()
        when (this.roleId) {
            1 -> authoritiesList.add(SimpleGrantedAuthority(UserRole.USER.roleName))
            2 -> {
                authoritiesList.add(SimpleGrantedAuthority(UserRole.USER.roleName))
                authoritiesList.add(SimpleGrantedAuthority(UserRole.ADMIN.roleName))
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
