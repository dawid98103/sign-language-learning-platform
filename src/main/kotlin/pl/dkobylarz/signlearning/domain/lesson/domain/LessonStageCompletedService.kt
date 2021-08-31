package pl.dkobylarz.signlearning.domain.lesson.domain

import pl.dkobylarz.signlearning.domain.lesson.domain.dto.LessonStageCompletedDto
import pl.dkobylarz.signlearning.domain.lesson.infrastructure.LessonStageCompletedDatabase

class LessonStageCompletedService(private val lessonStageCompletedDatabase: LessonStageCompletedDatabase) {

    fun getCompletedLessonsForUser(userId: Int): List<LessonStageCompletedDto> {
        return lessonStageCompletedDatabase.getCompletedLessonsForUser(userId).asSequence()
            .map { LessonMapper.toDto(it) }.toList()
    }
}