package pl.dkobylarz.signlearning.domain.lesson.domain

import pl.dkobylarz.signlearning.domain.lesson.domain.dto.LessonDto
import pl.dkobylarz.signlearning.domain.lesson.domain.dto.LessonStageElementDto
import pl.dkobylarz.signlearning.domain.lesson.infrastructure.LessonDatabase
import pl.dkobylarz.signlearning.domain.lesson.infrastructure.LessonStageDatabase
import java.util.stream.Collectors

class LessonService(private val lessonDatabase: LessonDatabase, private val lessonStageDatabase: LessonStageDatabase) {

    fun getLessons(): Set<LessonDto> {
        val lessons: MutableList<Lesson> = lessonDatabase.findAll()
        return lessons.stream()
            .map { LessonMapper.toDto(it) }
            .collect(Collectors.toSet())
    }

    fun getElementsForStage(stageId: Int): Set<LessonStageElementDto> {
        val stageElements = lessonStageDatabase.findElementsForStage(stageId)
        return stageElements.stream()
            .map { LessonMapper.toDto(it) }
            .collect(Collectors.toSet())
    }
}