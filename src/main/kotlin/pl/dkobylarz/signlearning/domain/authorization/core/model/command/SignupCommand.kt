package pl.dkobylarz.signlearning.domain.authorization.core.model.command

data class SignupCommand(val username: String, val email: String, val password: String, val avatarUrl: String)
