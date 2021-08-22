package pl.dkobylarz.signlearning.domain.lesson.infrastructure

import pl.dkobylarz.signlearning.domain.lesson.domain.Lesson

interface LessonDatabase {
    fun findAll(): MutableList<Lesson>
}