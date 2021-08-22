package pl.dkobylarz.signlearning.domain.lesson.infrastructure

import pl.dkobylarz.signlearning.domain.lesson.domain.Lesson

class LessonDatabaseAdapter(private val lessonRepository: LessonRepository) : LessonDatabase {
    override fun findAll(): MutableList<Lesson> {
        return lessonRepository.findAll().toMutableList()
    }
}