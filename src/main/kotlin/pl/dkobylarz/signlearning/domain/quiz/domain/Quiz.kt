package pl.dkobylarz.signlearning.domain.quiz.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table

//QUIZ AGGREGATE ROOT
/**
 * You should use aggregate root to all CRUD operations even if it is on branch e.g quizQuestion.
 * You should have one repository per aggregate root
 **/

@Table("quiz")
data class Quiz(
    @Id
    val quizId: Int,
    val title: String,
    val pointsToGain: Int,
    val lessonId: Int,
    @MappedCollection(idColumn = "quiz_id")
    val questions: Set<QuizQuestion>
) {
    fun getQuizQuestionIdById(quizQuestionId: Int): QuizQuestion {
        return questions.asSequence()
            .filter { it.quizQuestionId == quizQuestionId }
            .first()
    }

    fun getQuestionsForQuiz(): Set<QuizQuestion> {
        return questions
    }
}