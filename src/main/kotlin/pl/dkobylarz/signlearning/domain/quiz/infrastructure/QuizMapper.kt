package pl.dkobylarz.signlearning.domain.quiz.infrastructure

import pl.dkobylarz.signlearning.domain.quiz.domain.Quiz
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizDTO

class QuizMapper {

    companion object {
        fun toDto(quiz: Quiz): QuizDTO {
            return QuizDTO(
                quiz.quizId,
                quiz.title,
                quiz.lessonId,
                true
            )
        }
    }
}