package pl.dkobylarz.signlearning.domain.lessoncompleted.domain

import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageWithoutElementsDTO
import pl.dkobylarz.signlearning.domain.lessoncompleted.infrastructure.LessonStageCompletedRepository
import pl.dkobylarz.signlearning.domain.user.domain.User
import java.time.LocalDateTime

@Service
class LessonStageCompletedService(private val lessonStageCompletedRepository: LessonStageCompletedRepository) {

    fun setLessonStageAsCompletedByUser(stageId: Int, user: User?) {
        user?.let {
            setCompletedStatusForUserAndStage(it.userId!!, stageId)
        }
    }

    fun getCompletionStageStatusForUserMap(
        lessonStageWithoutElements: Set<LessonStageWithoutElementsDTO>,
        user: User
    ): Map<Int, Boolean> {
        return getCompletedStatusForUserAndLessonStage(lessonStageWithoutElements, user)
    }

    private fun setCompletedStatusForUserAndStage(userId: Int, stageId: Int) {
        if (!lessonStageCompletedRepository.existsByUserIdAndLessonStageId(userId, stageId)) {
            lessonStageCompletedRepository.save(
                LessonStageCompleted(
                    lessonStageCompletedId = 0,
                    lessonStageId = stageId,
                    userId = userId,
                    completionDate = LocalDateTime.now()
                )
            )
        }
    }

    private fun getCompletedStatusForUserAndLessonStage(
        lessonStageWithoutElements: Set<LessonStageWithoutElementsDTO>,
        user: User
    ): Map<Int, Boolean> {
        val completedLessons: List<LessonStageCompleted> =
            lessonStageCompletedRepository.findAllByUserId(user.userId!!)
        val completedStagesByUserIds: Set<Int> = completedLessons.asSequence()
            .map { it.lessonStageId }
            .toSet()
        val userStageCompletionMap: MutableMap<Int, Boolean> = mutableMapOf()

        lessonStageWithoutElements.asSequence()
            .forEach {
                userStageCompletionMap[it.lessonStageId] = completedStagesByUserIds.contains(it.lessonStageId)
            }

        return userStageCompletionMap
    }
}