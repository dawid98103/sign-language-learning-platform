package pl.dkobylarz.signlearning.domain.lesson.infrastructure

import pl.dkobylarz.signlearning.domain.lesson.domain.LessonStage
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageWithoutElementsDTO

interface LessonStageDatabase {
    fun save(lessonStage: LessonStage)
    fun findStagesForLessonWithoutElements(lessonId: Int): List<LessonStageWithoutElementsDTO>
    fun getByStageId(stageId: Int): LessonStage
}