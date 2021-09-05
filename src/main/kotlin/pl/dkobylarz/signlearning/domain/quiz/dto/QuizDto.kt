package pl.dkobylarz.signlearning.domain.quiz.dto

data class QuizDto(
    private val quizId: Int,
    private val name: String,
    private val available: Boolean
)
