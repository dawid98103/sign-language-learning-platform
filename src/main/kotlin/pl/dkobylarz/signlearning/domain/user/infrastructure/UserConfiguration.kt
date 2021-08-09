package pl.dkobylarz.signlearning.domain.user.infrastructure

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.dkobylarz.signlearning.domain.user.core.UserFacade
import pl.dkobylarz.signlearning.domain.user.core.model.UserService

@Configuration
class UserConfiguration {

    @Bean
    fun prodDatabase(userRepository: UserRepository): UserDatabase {
        return UserDatabaseAdapter(userRepository)
    }

    @Bean
    fun userFacade(userDatabase: UserDatabase): UserFacade {
        val userService = UserService(userDatabase)
        return UserFacade(userService)
    }
}