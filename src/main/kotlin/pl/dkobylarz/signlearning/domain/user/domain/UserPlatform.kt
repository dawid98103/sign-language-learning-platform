package pl.dkobylarz.signlearning.domain.user.domain

data class UserPlatform(
    val userId: Int,
    val username: String,
    val password: String,
    val name: String,
    val surname: String,
    val email: String,
    val roleId: Int
)
