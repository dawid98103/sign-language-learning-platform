package pl.dkobylarz.signlearning.domain.user.core.model

enum class UserRole(private val roleId: Int, private val roleName: String) {
    USER(1, "USER"),
    ADMIN(2, "ADMIN");

    companion object {
        fun roleByRoleId(roleId: Int): UserRole? {
            return values().find { userRole -> userRole.roleId == roleId }
        }
    }
}