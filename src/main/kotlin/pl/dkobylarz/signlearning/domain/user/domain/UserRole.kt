package pl.dkobylarz.signlearning.domain.user.domain

enum class UserRole(val roleId: Int, val roleName: String) {
    USER(1, "ROLE_USER"),
    ADMIN(2, "ROLE_ADMIN");
}