package pl.dkobylarz.signlearning.domain.user.infrastructure

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.dkobylarz.signlearning.domain.user.UserFacade
import pl.dkobylarz.signlearning.domain.user.domain.UserService

@Configuration
class UserConfiguration {

    @Bean
    @ConditionalOnProperty(name = ["signlearning.db.mock"], havingValue = "true")
    fun inMemDatabase() : UserDatabase {
        return UserDatabaseInMemoryAdapter()
    }

    @Bean
    @ConditionalOnProperty(name = ["signlearning.db.mock"], havingValue = "false")
    fun prodDatabase(userRepository: UserRepository): UserDatabase {
        return UserDatabaseAdapter(userRepository)
    }

    @Bean
    fun userFacade(userDatabase: UserDatabase): UserFacade {
        val userService = UserService(userDatabase)
        return UserFacade(userService)
    }
}