package pl.dkobylarz.signlearning.domain.quiz.infrastructure

import pl.dkobylarz.signlearning.domain.quiz.domain.QuizAnswer
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizAnswerWithoutCorrectDTO

class QuizAnswerMapper {
    companion object {
        fun toWithoutCorrectDto(quizAnswer: QuizAnswer): QuizAnswerWithoutCorrectDTO {
            return QuizAnswerWithoutCorrectDTO(
                quizAnswerId = quizAnswer.quizAnswerId,
                description = quizAnswer.description
            )
        }
    }
}