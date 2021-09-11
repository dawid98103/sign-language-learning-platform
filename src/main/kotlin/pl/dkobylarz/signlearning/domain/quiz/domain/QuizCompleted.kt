package pl.dkobylarz.signlearning.domain.quiz.domain

import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("quiz_completed")
data class QuizCompleted(
    val quizCompletedId: Int,
    val userId: Int,
    val quizId: Int,
    val result: Int,
    val completionDate: LocalDateTime
)
