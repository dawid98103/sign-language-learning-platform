package pl.dkobylarz.signlearning.domain.authorization.core.model

enum class Role(val roleId: Int) {
    USER(1),
    ADMIN(2)
}