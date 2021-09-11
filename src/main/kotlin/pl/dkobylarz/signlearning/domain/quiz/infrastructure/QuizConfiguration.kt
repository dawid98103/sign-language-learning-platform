package pl.dkobylarz.signlearning.domain.quiz.infrastructure

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.dkobylarz.signlearning.domain.quiz.QuizFacade
import pl.dkobylarz.signlearning.domain.quiz.domain.QuizService

@Configuration
class QuizConfiguration {

    @Bean
    fun quizProdDatabase(quizRepository: QuizRepository): QuizDatabase {
        return QuizDatabaseAdapter(quizRepository)
    }

    @Bean
    fun quizFacade(quizDatabase: QuizDatabase): QuizFacade {
        val quizService = QuizService(quizDatabase)
        return QuizFacade(quizService)
    }
}