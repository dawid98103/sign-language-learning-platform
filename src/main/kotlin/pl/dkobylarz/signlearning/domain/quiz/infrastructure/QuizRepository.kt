package pl.dkobylarz.signlearning.domain.quiz.infrastructure

import org.springframework.data.repository.CrudRepository
import pl.dkobylarz.signlearning.domain.quiz.domain.Quiz
import java.util.*

interface QuizRepository : CrudRepository<Quiz, Int> {
    override fun findAll(): Set<Quiz>
    fun findByLessonId(lessonId: Int): Optional<Quiz>
}