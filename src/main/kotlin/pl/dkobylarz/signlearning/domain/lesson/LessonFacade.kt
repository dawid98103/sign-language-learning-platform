package pl.dkobylarz.signlearning.domain.lesson

import org.springframework.transaction.annotation.Transactional
import pl.dkobylarz.signlearning.domain.lesson.domain.LessonService
import pl.dkobylarz.signlearning.domain.lesson.domain.dto.LessonDto
import pl.dkobylarz.signlearning.domain.lesson.domain.dto.LessonStageElementDto

@Transactional
class LessonFacade(private val lessonService: LessonService) {

    fun getLessons(): Set<LessonDto> {
        return lessonService.getLessons()
    }

    fun getElementsForStage(stageId: Int): Set<LessonStageElementDto> {
        return lessonService.getElementsForStage(stageId)
    }
}