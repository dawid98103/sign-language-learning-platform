package pl.dkobylarz.signlearning.domain.user.domain.command

data class AddUserCommand(
    val username: String,
    val password: String,
    val name: String,
    val surname: String,
    val email: String,
    val roleId: Int,
    val avatarUrl: String
)
