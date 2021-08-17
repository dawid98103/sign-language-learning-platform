package pl.dkobylarz.signlearning.domain.authorization.domain.command

data class LoginCommand(val username: String, val password: String)
