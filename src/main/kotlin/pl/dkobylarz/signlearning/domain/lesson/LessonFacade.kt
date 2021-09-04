package pl.dkobylarz.signlearning.domain.lesson

import org.springframework.transaction.annotation.Transactional
import pl.dkobylarz.signlearning.domain.lesson.domain.LessonService
import pl.dkobylarz.signlearning.domain.lesson.domain.LessonStageService
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageCompletionDto
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageElementDto
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonWithCompletionStatusDto
import pl.dkobylarz.signlearning.domain.user.domain.User

@Transactional
class LessonFacade(
    private val lessonService: LessonService,
    private val lessonStageService: LessonStageService
) {

    fun getLessons(): Set<LessonWithCompletionStatusDto> {
        return lessonService.getLessons()
    }

    fun getElementsForLessonStage(stageId: Int): Set<LessonStageElementDto> {
        return lessonStageService.getElementsForLessonStage(stageId)
    }

    fun getStagesForLessonWithCompletionStatus(lessonId: Int, user: User?): Set<LessonStageCompletionDto> {
        return lessonStageService.getStagesForLessonWithCompletionStatus(lessonId, user)
    }
}