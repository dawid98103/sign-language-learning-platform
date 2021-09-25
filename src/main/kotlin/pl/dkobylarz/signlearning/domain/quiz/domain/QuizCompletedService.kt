package pl.dkobylarz.signlearning.domain.quiz.domain

import pl.dkobylarz.signlearning.domain.quiz.dto.QuizProcessDTO
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizQuestionDTO
import pl.dkobylarz.signlearning.domain.quiz.infrastructure.QuizCompletedDatabase
import java.time.LocalDateTime

class QuizCompletedService(private val quizCompletedDatabase: QuizCompletedDatabase) {

    fun finishQuizForUser(userId: Int, quizId: Int, quizProcessDTO: QuizProcessDTO) {
        val gainedPoints = calculateGainedPoints(quizProcessDTO.userAnswers)
        quizCompletedDatabase.save(QuizCompleted(
                0,
                userId,
                quizId,
                gainedPoints,
                LocalDateTime.now()
        ))

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
