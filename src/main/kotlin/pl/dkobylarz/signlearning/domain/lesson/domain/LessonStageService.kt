package pl.dkobylarz.signlearning.domain.lesson.domain

import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageCompletionDto
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageWithoutElementsDto
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageElementDto
import pl.dkobylarz.signlearning.domain.lesson.infrastructure.LessonStageDatabase
import pl.dkobylarz.signlearning.domain.user.domain.User

class LessonStageService(
    val lessonStageDatabase: LessonStageDatabase,
    val lessonStageCompletedService: LessonStageCompletedService
) {

    fun getStagesForLessonWithCompletionStatus(lessonId: Int, user: User?): Set<LessonStageCompletionDto> {
        val lessonStageWithoutElements: Set<LessonStageWithoutElementsDto> = lessonStageDatabase.findStagesForLessonWithoutElements(lessonId)
            .toSet()

        val lessonStageCompletionSet: MutableSet<LessonStageCompletionDto> = mutableSetOf()
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

    fun getElementsForLessonStage(stageId: Int): Set<LessonStageElementDto> {
        val lessonStage: LessonStage = lessonStageDatabase.getByStageId(stageId)

        return lessonStage.lessonStageElements.asSequence()
            .map { LessonMapper.toDto(it) }
            .toSet()
    }

    private fun getStatus(completionStageStatusMap: Map<Int, Boolean>, lessonStageWithoutElementsDto: LessonStageWithoutElementsDto): Boolean {
        return completionStageStatusMap[lessonStageWithoutElementsDto.lessonStageId]!!
    }

    private fun getCompletionStageStatusForUserMap(lessonStageWithoutElements: Set<LessonStageWithoutElementsDto>, user: User): Map<Int, Boolean> {
        return lessonStageCompletedService.getCompletedStatusForUserAndLessonStage(lessonStageWithoutElements, user)
    }
}