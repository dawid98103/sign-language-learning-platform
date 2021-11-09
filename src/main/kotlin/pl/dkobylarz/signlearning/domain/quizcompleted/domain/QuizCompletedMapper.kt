package pl.dkobylarz.signlearning.domain.quizcompleted.domain

import pl.dkobylarz.signlearning.domain.quizcompleted.dto.QuizCompletedShortDTO

class QuizCompletedMapper {

    companion object {
        fun toDto(quizCompleted: QuizCompleted): QuizCompletedShortDTO {
            return QuizCompletedShortDTO(
                quizCompleted.userId,
                quizCompleted.quizId
            )
        }
    }
}