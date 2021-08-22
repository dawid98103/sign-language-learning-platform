package pl.dkobylarz.signlearning.domain.lesson.infrastructure

import pl.dkobylarz.signlearning.domain.lesson.domain.LessonStage
import pl.dkobylarz.signlearning.domain.lesson.domain.LessonStageElement

class LessonStageDatabaseAdapter(private val lessonStageRepository: LessonStageRepository) : LessonStageDatabase {

    override fun findElementsForStage(stageId: Int): MutableList<LessonStageElement> {
        val lessonStage: LessonStage = lessonStageRepository.findById(stageId).orElseThrow()
        return lessonStage.lessonStages.toMutableList()
    }
}