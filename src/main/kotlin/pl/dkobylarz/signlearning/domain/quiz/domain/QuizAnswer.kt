package pl.dkobylarz.signlearning.domain.quiz.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("quiz_answer")
data class QuizAnswer(
    @Id
    val quizAnswerId: Int,
    val quizQuestionId: Int,
    val description: String,
    val correct: Boolean
)
