package pl.dkobylarz.signlearning.domain.quiz.domain

import pl.dkobylarz.signlearning.domain.quiz.dto.QuizCompletedResultShortDTO
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizCompletionStatusDTO
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizDTO
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizResultDTO

class QuizMapper {

    companion object {
        fun toDto(quiz: Quiz): QuizDTO {
            return QuizDTO(
                quiz.quizId,
                quiz.title,
                quiz.lessonId
            )
        }

        fun toDto(quiz: Quiz, quizResultDTO: QuizResultDTO?): QuizCompletionStatusDTO {
            return QuizCompletionStatusDTO(
                quiz.quizId,
                quiz.title,
                quiz.lessonId,
                quizResultDTO
            )
        }

        fun toDto(quiz: Quiz, completedByUser: Boolean): QuizCompletedResultShortDTO {
            return QuizCompletedResultShortDTO(
                quiz.lessonId,
                quiz.quizId,
                quiz.title,
                completedByUser
            )
        }
    }
}