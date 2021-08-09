package pl.dkobylarz.signlearning.domain.authorization.core.model

data class JwtResponse(
    val token: String,
    val id: Int?,
    val username: String,
    val email: String?,
    val roles: List<String>?
)
