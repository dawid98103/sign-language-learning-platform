package pl.dkobylarz.signlearning.domain.authorization.domain

enum class Role(val roleId: Int) {
    USER(1),
    ADMIN(2)
}