package pl.dkobylarz.signlearning.domain.lesson.domain

import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageCompletionDTO
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageWithoutElementsDTO
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageElementDTO
import pl.dkobylarz.signlearning.domain.lesson.infrastructure.LessonMapper
import pl.dkobylarz.signlearning.domain.lesson.infrastructure.LessonStageDatabase
import pl.dkobylarz.signlearning.domain.user.domain.User

class LessonStageService(
    val lessonStageDatabase: LessonStageDatabase,
    val lessonStageCompletedService: LessonStageCompletedService
) {

    fun getStagesForLessonWithCompletionStatus(lessonId: Int, user: User?): Set<LessonStageCompletionDTO> {
        val lessonStageWithoutElements: Set<LessonStageWithoutElementsDTO> = lessonStageDatabase.findStagesForLessonWithoutElements(lessonId)
            .toSet()

        val lessonStageCompletionSet: MutableSet<LessonStageCompletionDTO> = mutableSetOf()
        user?.let {
            val completionStageStatusForUser: Map<Int, Boolean> = getCompletionStageStatusForUserMap(lessonStageWithoutElements, user)

            lessonStageWithoutElements.asSequence()
                .map {
                    LessonMapper.toLessonStageCompletionDto(
                        it,
                        getStatus(completionStageStatusForUser, it)
                    )
                }
                .forEach { lessonStageCompletionSet.add(it) }
        } ?: kotlin.run {
            lessonStageWithoutElements.asSequence()
                .map {
                    LessonMapper.toLessonStageCompletionDto(
                        it,
                        false
                    )
                }
                .forEach { lessonStageCompletionSet.add(it) }
        }
        return lessonStageCompletionSet.asSequence()
            .sortedBy { it.index }
            .toSet()
    }

    fun setLessonStageAsCompletedByUser(stageId: Int, user: User?) {
        user?.let {
            lessonStageCompletedService.setCompletedStatusForUserAndStage(it.userId!!, stageId)
        }
    }

    fun getElementsForLessonStage(stageId: Int): Set<LessonStageElementDTO> {
        val lessonStage: LessonStage = lessonStageDatabase.getByStageId(stageId)

        return lessonStage.lessonStageElements.asSequence()
            .map { LessonMapper.toDto(it) }
            .toSet()
    }

    private fun getStatus(completionStageStatusMap: Map<Int, Boolean>, lessonStageWithoutElementsDTO: LessonStageWithoutElementsDTO): Boolean {
        return completionStageStatusMap[lessonStageWithoutElementsDTO.lessonStageId]!!
    }

    private fun getCompletionStageStatusForUserMap(lessonStageWithoutElements: Set<LessonStageWithoutElementsDTO>, user: User): Map<Int, Boolean> {
        return lessonStageCompletedService.getCompletedStatusForUserAndLessonStage(lessonStageWithoutElements, user)
    }
}