package pl.dkobylarz.signlearning.domain.authorization.dto

data class SignupRequestDTO(
    val username: String,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val passwordRepeat: String,
    val avatarUrl: String
)
