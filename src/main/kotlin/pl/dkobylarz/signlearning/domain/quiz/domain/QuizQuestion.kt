package pl.dkobylarz.signlearning.domain.quiz.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table

@Table("quiz_question")
data class QuizQuestion(
    @Id
    val quizQuestionId: Int,
    val quizId: Int,
    val questionNumber: Int,
    val questionName: String,
    val points: Int,
    val videoUrl: String,
    @MappedCollection(idColumn = "quiz_question_id")
    val answers: Set<QuizAnswer>
)