package pl.dkobylarz.signlearning.domain.quizcompleted.domain

import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizQuestionDTO
import pl.dkobylarz.signlearning.domain.quizcompleted.infrastructure.QuizCompletedRepository
import pl.dkobylarz.signlearning.domain.quizprocess.dto.QuizProcessDTO
import java.time.LocalDateTime

@Service
class QuizCompletedService(
    private val quizCompletedRepository: QuizCompletedRepository
) {

    fun finishQuizForUser(userId: Int, quizId: Int, startDate: LocalDateTime, quizProcessDTO: QuizProcessDTO) {
        val gainedPoints = calculateGainedPoints(quizProcessDTO.userAnswers)
        quizCompletedRepository.save(
            QuizCompleted(
                0,
                userId,
                quizId,
                gainedPoints,
                startDate,
                LocalDateTime.now()
            )
        )
    }

    fun isQuizCompletedByUser(userId: Int, quizId: Int): Boolean {
        return quizCompletedRepository.findByQuizIdAndUserId(quizId, userId) !== null
    }

    private fun calculateGainedPoints(answerMap: Map<QuizQuestionDTO, Boolean>): Int {
        var sum = 0;
        answerMap.forEach { (k, v) ->
            if (v) {
                sum += k.points
            }
        }
        return sum
    }
}
