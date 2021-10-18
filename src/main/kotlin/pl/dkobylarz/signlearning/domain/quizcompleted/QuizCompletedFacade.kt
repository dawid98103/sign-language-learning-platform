package pl.dkobylarz.signlearning.domain.quizcompleted

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.dkobylarz.signlearning.domain.quiz.domain.Quiz
import pl.dkobylarz.signlearning.domain.quizprocess.dto.QuizProcessDTO
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizResultDTO
import pl.dkobylarz.signlearning.domain.quizcompleted.domain.QuizCompletedResultService
import pl.dkobylarz.signlearning.domain.quizcompleted.domain.QuizCompletedService
import java.time.LocalDateTime

@Service
@Transactional
class QuizCompletedFacade(
    private val quizCompletedService: QuizCompletedService,
    private val quizCompletedResultService: QuizCompletedResultService
) {

    fun markAsCompletedForUser(userId: Int, quizId: Int, startDate: LocalDateTime, quizProcessDTO: QuizProcessDTO) {
        quizCompletedService.finishQuizForUser(userId, quizId, startDate, quizProcessDTO)
    }

    fun getQuizResult(quiz: Quiz, userId: Int): QuizResultDTO? {
        return quizCompletedResultService.getQuizResult(quiz, userId)
    }

    fun isQuizCompleted(userId: Int, quizId: Int): Boolean {
        return quizCompletedService.isQuizCompletedByUser(userId, quizId)
    }
}