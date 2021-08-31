package pl.dkobylarz.signlearning.domain.lesson.infrastructure

import pl.dkobylarz.signlearning.domain.lesson.domain.LessonStageCompleted

interface LessonStageCompletedDatabase {
    fun getCompletedLessonsForUser(userId: Int): List<LessonStageCompleted>
}