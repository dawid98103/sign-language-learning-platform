package pl.dkobylarz.signlearning.domain.quiz.infrastructure

import pl.dkobylarz.signlearning.domain.quiz.domain.Quiz
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizDto

class QuizMapper {

    companion object {
        fun toDto(quiz: Quiz): QuizDto {
            return QuizDto(
                quiz.quizId,
                quiz.title,
                quiz.lessonId,
                true
            )
        }
    }
}