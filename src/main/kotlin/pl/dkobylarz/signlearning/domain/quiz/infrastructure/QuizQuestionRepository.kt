package pl.dkobylarz.signlearning.domain.quiz.infrastructure

import org.springframework.data.repository.CrudRepository
import pl.dkobylarz.signlearning.domain.quiz.domain.QuizQuestion

interface QuizQuestionRepository : CrudRepository<QuizQuestion, Int> {
    fun findByQuizId(quizId: Int): Set<QuizQuestion>
}