package pl.dkobylarz.signlearning.domain.authorization.dto

import pl.dkobylarz.signlearning.domain.user.domain.UserRole

data class JwtResponseDTO(
    val token: String,
    val id: Int?,
    val username: String,
    val email: String?,
    val roles: List<UserRole>?
)
