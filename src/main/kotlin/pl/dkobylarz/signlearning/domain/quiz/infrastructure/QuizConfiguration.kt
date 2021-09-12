package pl.dkobylarz.signlearning.domain.quiz.infrastructure

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.dkobylarz.signlearning.domain.quiz.QuizFacade
import pl.dkobylarz.signlearning.domain.quiz.domain.QuizQuestionService
import pl.dkobylarz.signlearning.domain.quiz.domain.QuizService

@Configuration
class QuizConfiguration {

    @Bean
    fun quizProdDatabase(quizRepository: QuizRepository): QuizDatabase {
        return QuizDatabaseAdapter(quizRepository)
    }

    @Bean
    fun quizQuestionProdDatabase(quizQuestionRepository: QuizQuestionRepository): QuizQuestionDatabase {
        return QuizQuestionDatabaseAdapter(quizQuestionRepository)
    }

    @Bean
    fun quizFacade(quizDatabase: QuizDatabase, quizQuestionDatabase: QuizQuestionDatabase): QuizFacade {
        val quizService = QuizService(quizDatabase)
        val quizQuestionService = QuizQuestionService(quizQuestionDatabase)
        return QuizFacade(quizService, quizQuestionService)
    }
}