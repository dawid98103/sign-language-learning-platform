package pl.dkobylarz.signlearning.domain.quiz.dto

data class QuizCompletionStatusDTO(
    val quizId: Int,
    val title: String,
    val lessonId: Int,
    val quizResult: QuizResultDTO? = null
)
