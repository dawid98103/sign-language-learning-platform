package pl.dkobylarz.signlearning.domain.authorization.dto

import pl.dkobylarz.signlearning.domain.user.constant.UserRole

data class JwtResponseDTO(
    val token: String,
    val id: Int?,
    val username: String,
    val avatarUrl: String,
    val email: String?,
    val roles: List<UserRole>?
)
