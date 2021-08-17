package pl.dkobylarz.signlearning.domain.authorization.domain.command

data class SignupCommand(
    val username: String,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val avatarUrl: String
)
