package pl.dkobylarz.signlearning.domain.quiz.infrastructure

import pl.dkobylarz.signlearning.domain.quiz.domain.QuizAnswer
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizAnswerWithoutCorrectDto

class QuizAnswerMapper {
    companion object {
        fun toWithoutCorrectDto(quizAnswer: QuizAnswer): QuizAnswerWithoutCorrectDto {
            return QuizAnswerWithoutCorrectDto(
                quizAnswerId = quizAnswer.quizAnswerId,
                description = quizAnswer.description
            )
        }
    }
}