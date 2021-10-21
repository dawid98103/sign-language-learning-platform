package pl.dkobylarz.signlearning.domain.lesson

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.dkobylarz.signlearning.domain.lesson.domain.LessonService
import pl.dkobylarz.signlearning.domain.lesson.domain.LessonStageService
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageCompletionDTO
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageDTO
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageElementDTO
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonWithCompletionStatusDTO
import pl.dkobylarz.signlearning.domain.user.domain.User

@Service
@Transactional
class LessonFacade(
    private val lessonService: LessonService,
    private val lessonStageService: LessonStageService
) {

    fun getLessons(): Set<LessonWithCompletionStatusDTO> {
        return lessonService.getLessons()
    }

    fun getStagesForLesson(lessonId: Int): Set<LessonStageDTO> {
        return lessonStageService.getStagesForLesson(lessonId)
    }

    fun getElementsForLessonStage(lessonId: Int, stageId: Int): Set<LessonStageElementDTO> {
        return lessonStageService.getElementsForLessonStage(lessonId, stageId)
    }

    fun getStagesForLessonWithCompletionStatus(lessonId: Int, user: User?): Set<LessonStageCompletionDTO> {
        return lessonStageService.getStagesForLessonWithCompletionStatus(lessonId, user)
    }
}