package pl.dkobylarz.signlearning.domain.lessoncompleted

import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageWithoutElementsDTO
import pl.dkobylarz.signlearning.domain.lessoncompleted.domain.LessonStageCompletedService
import pl.dkobylarz.signlearning.domain.user.domain.User

@Service
class LessonCompletedFacade(
    private val lessonStageCompletedService: LessonStageCompletedService
) {

    fun setLessonStageAsCompletedByUser(stageId: Int, user: User) {
        lessonStageCompletedService.setLessonStageAsCompletedByUser(stageId, user)
    }

    fun getCompletionStageStatusForUserMap(
        lessonStageWithoutElements: Set<LessonStageWithoutElementsDTO>,
        user: User
    ): Map<Int, Boolean> {
        return lessonStageCompletedService.getCompletionStageStatusForUserMap(lessonStageWithoutElements, user)
    }
}