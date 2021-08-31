package pl.dkobylarz.signlearning.domain.lesson.domain

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import pl.dkobylarz.signlearning.domain.lesson.domain.dto.LessonWithCompletitionStatusDto
import pl.dkobylarz.signlearning.domain.lesson.domain.dto.LessonStageElementDto
import pl.dkobylarz.signlearning.domain.lesson.infrastructure.LessonDatabase
import pl.dkobylarz.signlearning.domain.lesson.infrastructure.LessonStageDatabase
import java.util.stream.Collectors

class LessonService(private val lessonDatabase: LessonDatabase, private val lessonStageDatabase: LessonStageDatabase) {

    fun getLessons(): LinkedHashSet<LessonWithCompletitionStatusDto> {
        val lessons: MutableList<Lesson> = lessonDatabase.findAll()
        return lessons.stream()
            .map { LessonMapper.toDto(it) }
            .sorted { o1, o2 ->
                when {
                    (o1 == null && o2 == null) -> 0
                    (o1.lessonId < o2.lessonId) -> -1
                    else -> 1
                }
            }
            .collect(Collectors.toCollection { LinkedHashSet() })
    }

    fun getElementsForStage(stageId: Int): Set<LessonStageElementDto> {
        val stageElements = lessonStageDatabase.findElementsForStage(stageId)
        return stageElements.stream()
            .map { LessonMapper.toDto(it) }
            .collect(Collectors.toSet())
    }


}