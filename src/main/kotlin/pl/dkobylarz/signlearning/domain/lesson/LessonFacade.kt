package pl.dkobylarz.signlearning.domain.lesson

import org.springframework.transaction.annotation.Transactional
import pl.dkobylarz.signlearning.domain.lesson.domain.LessonService
import pl.dkobylarz.signlearning.domain.lesson.domain.LessonStageCompletedService
import pl.dkobylarz.signlearning.domain.lesson.domain.dto.LessonWithCompletitionStatusDto
import pl.dkobylarz.signlearning.domain.lesson.domain.dto.LessonStageCompletedDto
import pl.dkobylarz.signlearning.domain.lesson.domain.dto.LessonStageElementDto

@Transactional
class LessonFacade(
    private val lessonService: LessonService,
    private val lessonStageCompletedService: LessonStageCompletedService
) {

    fun getLessons(): Set<LessonWithCompletitionStatusDto> {
        return lessonService.getLessons()
    }

    fun getElementsForStage(stageId: Int): Set<LessonStageElementDto> {
        return lessonService.getElementsForStage(stageId)
    }

    fun getCompletedLessonsForUser(userId: Int): List<LessonStageCompletedDto> {
        return lessonStageCompletedService.getCompletedLessonsForUser(userId)
    }
}