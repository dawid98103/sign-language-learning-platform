package pl.dkobylarz.signlearning.domain.lesson.infrastructure

import org.springframework.data.repository.CrudRepository
import pl.dkobylarz.signlearning.domain.lesson.domain.Lesson

interface LessonRepository: CrudRepository<Lesson, Int> {
}