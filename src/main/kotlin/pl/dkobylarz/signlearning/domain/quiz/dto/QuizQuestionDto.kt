package pl.dkobylarz.signlearning.domain.quiz.dto

data class QuizQuestionDto(
    val quizQuestionId: Int,
    val questionNumber: Int,
    val questionName: String,
    val points: Int,
    val videoUrl: String,
    val answers: Set<QuizAnswerWithoutCorrectDto>
)
