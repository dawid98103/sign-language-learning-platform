package pl.dkobylarz.signlearning.domain.authorization.core.model.command

data class LoginCommand(val username: String, val password: String)
