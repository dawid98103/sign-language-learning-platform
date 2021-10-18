package pl.dkobylarz.signlearning.domain.quiz.dto

data class QuizResultEntryDTO(
    val questionNumber: Int,
    val question: String,
    val correctAnswer: String
)
