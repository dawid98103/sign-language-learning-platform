package pl.dkobylarz.signlearning.domain.quiz.dto

data class QuizCompletedResultShortDTO(
    val lessonId: Int,
    val quizId: Int,
    val quizName: String,
    val completed: Boolean
)
