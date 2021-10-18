package pl.dkobylarz.signlearning.domain.quiz.infrastructure

import org.springframework.data.repository.CrudRepository
import pl.dkobylarz.signlearning.domain.quiz.domain.Quiz

interface QuizRepository : CrudRepository<Quiz, Int> {
    fun findByQuizId(quizId: Int): Quiz
    override fun findAll(): Set<Quiz>
    fun findByLessonId(lessonId: Int): Quiz
}