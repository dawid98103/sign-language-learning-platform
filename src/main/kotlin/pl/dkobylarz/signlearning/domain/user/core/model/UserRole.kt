package pl.dkobylarz.signlearning.domain.user.core.model

enum class UserRole(val roleId: Int, val roleName: String) {
    USER(1, "ROLE_USER"),
    ADMIN(2, "ROLE_ADMIN");

    companion object {
        fun roleByRoleId(roleId: Int): UserRole? {
            return values().find { userRole -> userRole.roleId == roleId }
        }
    }
}