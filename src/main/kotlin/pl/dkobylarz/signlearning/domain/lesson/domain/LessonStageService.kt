package pl.dkobylarz.signlearning.domain.lesson.domain

import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageCompletionDTO
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageDTO
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageElementDTO
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageWithoutElementsDTO
import pl.dkobylarz.signlearning.domain.lesson.infrastructure.LessonRepository
import pl.dkobylarz.signlearning.domain.lessoncompleted.LessonCompletedFacade
import pl.dkobylarz.signlearning.domain.lessoncompleted.domain.LessonStageCompletedService
import pl.dkobylarz.signlearning.domain.user.domain.User

@Service
class LessonStageService(
    val lessonRepository: LessonRepository,
    val lessonCompletedFacade: LessonCompletedFacade,
    val lessonStageCompletedService: LessonStageCompletedService
) {

    fun getStagesForLessonWithCompletionStatus(lessonId: Int, user: User?): Set<LessonStageCompletionDTO> {
        val lesson: Lesson = lessonRepository.findByLessonId(lessonId)
        val lessonStageWithoutElements: Set<LessonStageWithoutElementsDTO> =
            lesson.getStagesForLesson().asSequence()
                .map { LessonMapper.toDto(it) }
                .toSet()

        val lessonStageCompletionSet: MutableSet<LessonStageCompletionDTO> = mutableSetOf()
        user?.let {
            val completionStageStatusForUser: Map<Int, Boolean> =
                lessonCompletedFacade.getCompletionStageStatusForUserMap(lessonStageWithoutElements, user)

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

    fun getStagesForLesson(lessonId: Int): Set<LessonStageDTO> {
        val lesson: Lesson = lessonRepository.findByLessonId(lessonId)
        val lessonStages = lesson.getStagesForLesson()
        return lessonStages.asSequence()
            .map { LessonMapper.toLessonStageDto(it) }
            .toSet()
    }

    fun getElementsForLessonStage(lessonId: Int, stageId: Int): Set<LessonStageElementDTO> {
        val lesson: Lesson = lessonRepository.findByLessonId(lessonId)
        val lessonStage: LessonStage = lesson.getLessonStageById(stageId)

        return lessonStage.lessonStageElements.asSequence()
            .map { LessonMapper.toDto(it) }
            .toSet()
    }

    fun getStageQuantityForLesson(lessonId: Int): Int {
        val lesson: Lesson = lessonRepository.findByLessonId(lessonId)
        return lesson.lessonStages.count()
    }

    private fun getStatus(
        completionStageStatusMap: Map<Int, Boolean>,
        lessonStageWithoutElementsDTO: LessonStageWithoutElementsDTO
    ): Boolean {
        return completionStageStatusMap[lessonStageWithoutElementsDTO.lessonStageId]!!
    }
}