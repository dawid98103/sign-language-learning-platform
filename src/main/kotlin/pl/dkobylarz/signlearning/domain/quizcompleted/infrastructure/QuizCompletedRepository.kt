package pl.dkobylarz.signlearning.domain.quizcompleted.infrastructure

import org.springframework.data.repository.CrudRepository
import pl.dkobylarz.signlearning.domain.quizcompleted.domain.QuizCompleted

interface QuizCompletedRepository : CrudRepository<QuizCompleted, Int> {
    fun findByQuizIdAndUserId(quizId: Int, userId: Int): QuizCompleted?
}