package pl.dkobylarz.signlearning.domain.quiz.infrastructure

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.dkobylarz.signlearning.domain.quiz.QuizFacade
import pl.dkobylarz.signlearning.domain.quiz.domain.QuizCompletedService
import pl.dkobylarz.signlearning.domain.quiz.domain.QuizProcessService
import pl.dkobylarz.signlearning.domain.quiz.domain.QuizQuestionService
import pl.dkobylarz.signlearning.domain.quiz.domain.QuizService
import java.util.concurrent.ConcurrentHashMap

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
    fun quizCompletedProdDatabase(quizCompletedRepository: QuizCompletedRepository): QuizCompletedDatabase {
        return QuizCompletedDatabaseAdapter(quizCompletedRepository)
    }

    @Bean
    fun quizFacade(quizDatabase: QuizDatabase, quizQuestionDatabase: QuizQuestionDatabase, quizCompletedDatabase: QuizCompletedDatabase): QuizFacade {
        val quizService = QuizService(quizDatabase)
        val quizQuestionService = QuizQuestionService(quizQuestionDatabase)
        val quizCompletedService = QuizCompletedService(quizCompletedDatabase)
        val quizProcessService = QuizProcessService(ConcurrentHashMap(), quizCompletedService, quizQuestionService)
        return QuizFacade(quizService, quizProcessService, quizQuestionService)
    }
}