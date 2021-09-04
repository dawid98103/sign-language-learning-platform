package pl.dkobylarz.signlearning.domain.lesson.domain

import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageWithoutElementsDto
import pl.dkobylarz.signlearning.domain.lesson.infrastructure.LessonStageCompletedDatabase
import pl.dkobylarz.signlearning.domain.user.domain.User

class LessonStageCompletedService(private val lessonStageCompletedDatabase: LessonStageCompletedDatabase) {

    fun getCompletedStatusForUserAndLessonStage(lessonStageWithoutElements: Set<LessonStageWithoutElementsDto>, user: User): Map<Int, Boolean> {
        val completedLessons: List<LessonStageCompleted> =
            lessonStageCompletedDatabase.getCompletedLessonsForUser(user.userId!!)
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