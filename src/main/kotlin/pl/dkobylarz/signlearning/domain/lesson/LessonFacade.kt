package pl.dkobylarz.signlearning.domain.lesson

import org.springframework.transaction.annotation.Transactional
import pl.dkobylarz.signlearning.domain.lesson.domain.LessonService
import pl.dkobylarz.signlearning.domain.lesson.domain.LessonStageService
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageCompletionDTO
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageElementDTO
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonWithCompletionStatusDTO
import pl.dkobylarz.signlearning.domain.user.domain.User

@Transactional
class LessonFacade(
    private val lessonService: LessonService,
    private val lessonStageService: LessonStageService
) {

    fun getLessons(): Set<LessonWithCompletionStatusDTO> {
        return lessonService.getLessons()
    }

    fun getElementsForLessonStage(stageId: Int): Set<LessonStageElementDTO> {
        return lessonStageService.getElementsForLessonStage(stageId)
    }

    fun getStagesForLessonWithCompletionStatus(lessonId: Int, user: User?): Set<LessonStageCompletionDTO> {
        return lessonStageService.getStagesForLessonWithCompletionStatus(lessonId, user)
    }

    fun setLessonStageAsCompletedByUser(stageId: Int, user: User?) {
        lessonStageService.setLessonStageAsCompletedByUser(stageId, user)
    }
}