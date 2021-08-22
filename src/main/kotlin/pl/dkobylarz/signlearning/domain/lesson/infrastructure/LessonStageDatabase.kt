package pl.dkobylarz.signlearning.domain.lesson.infrastructure

import pl.dkobylarz.signlearning.domain.lesson.domain.LessonStageElement

interface LessonStageDatabase {
    fun findElementsForStage(stageId: Int): MutableList<LessonStageElement>
}