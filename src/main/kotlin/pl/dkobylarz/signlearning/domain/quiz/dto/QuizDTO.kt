package pl.dkobylarz.signlearning.domain.quiz.dto

data class QuizDTO(
    val quizId: Int,
    val title: String,
    val lessonId: Int,
    val available: Boolean
)
