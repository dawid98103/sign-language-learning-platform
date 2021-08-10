package pl.dkobylarz.signlearning.domain.user.core.model

data class UserPlatform(
    val id: Int,
    val username: String,
    val password: String,
    val name: String,
    val surname: String,
    val email: String,
    val roleId: Int
)
