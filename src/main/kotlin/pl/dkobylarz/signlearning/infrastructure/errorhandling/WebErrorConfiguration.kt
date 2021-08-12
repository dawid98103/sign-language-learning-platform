package pl.dkobylarz.signlearning.infrastructure.errorhandling

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.servlet.error.ErrorAttributes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WebErrorConfiguration {

    @Value("\${signlearning.api.version}")
    lateinit var apiVersion: String

    @Bean
    fun errorAttributes(): ErrorAttributes {
        return SignLearningErrorAttributes(apiVersion)
    }
}