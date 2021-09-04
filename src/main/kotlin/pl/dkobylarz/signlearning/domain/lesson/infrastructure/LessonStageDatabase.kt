package pl.dkobylarz.signlearning.domain.lesson.infrastructure

import pl.dkobylarz.signlearning.domain.lesson.domain.LessonStage
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageWithoutElementsDto

interface LessonStageDatabase {
    fun save(lessonStage: LessonStage)
    fun findStagesForLessonWithoutElements(lessonId: Int): List<LessonStageWithoutElementsDto>
    fun getByStageId(stageId: Int): LessonStage
}