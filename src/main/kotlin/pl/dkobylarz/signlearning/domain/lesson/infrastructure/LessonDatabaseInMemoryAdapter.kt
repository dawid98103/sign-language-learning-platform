package pl.dkobylarz.signlearning.domain.lesson.infrastructure

import pl.dkobylarz.signlearning.domain.lesson.domain.Lesson
import java.util.concurrent.ConcurrentHashMap

class LessonDatabaseInMemoryAdapter : LessonDatabase {

    lateinit var lessonDatabase: ConcurrentHashMap<Int, Lesson>

    override fun findAll(): MutableList<Lesson> {
        return lessonDatabase.values.toMutableList()
    }
}