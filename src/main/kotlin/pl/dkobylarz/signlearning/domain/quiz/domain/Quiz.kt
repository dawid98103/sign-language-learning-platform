package pl.dkobylarz.signlearning.domain.quiz.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table

@Table("quiz")
data class Quiz(
    @Id
    val quizId: Int,
    val title: String,
    val pointsToGain: Int,
    val lessonId: Int,
    @MappedCollection(idColumn = "quiz_id")
    val questions: Set<QuizQuestion>
)