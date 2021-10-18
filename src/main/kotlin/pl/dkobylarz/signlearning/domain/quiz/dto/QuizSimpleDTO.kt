package pl.dkobylarz.signlearning.domain.quiz.dto

data class QuizSimpleDTO(
    val quizId: Int,
    val title: String,
    val pointsToGain: Int,
    val lessonId: Int
)
