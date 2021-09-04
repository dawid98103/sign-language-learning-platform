package pl.dkobylarz.signlearning.domain.lesson.infrastructure

import pl.dkobylarz.signlearning.domain.lesson.domain.LessonStage
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageWithoutElementsDto
import pl.dkobylarz.signlearning.domain.lesson.exception.StageNotFoundException

class LessonStageDatabaseAdapter(private val lessonStageRepository: LessonStageRepository) : LessonStageDatabase {

    override fun save(lessonStage: LessonStage) {
        lessonStageRepository.save(lessonStage)
    }

    override fun findStagesForLessonWithoutElements(lessonId: Int): List<LessonStageWithoutElementsDto> {
        return lessonStageRepository.findByLessonId(lessonId)
    }

    override fun getByStageId(stageId: Int): LessonStage {
        return lessonStageRepository.findById(stageId).orElseThrow { StageNotFoundException() }
    }
}