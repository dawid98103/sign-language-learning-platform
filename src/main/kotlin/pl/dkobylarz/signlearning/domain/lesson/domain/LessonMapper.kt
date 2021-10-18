package pl.dkobylarz.signlearning.domain.lesson.domain

import pl.dkobylarz.signlearning.domain.lesson.domain.Lesson
import pl.dkobylarz.signlearning.domain.lesson.domain.LessonStage
import pl.dkobylarz.signlearning.domain.lesson.domain.LessonStageElement
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageCompletionDTO
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageWithoutElementsDTO
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageElementDTO
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonWithCompletionStatusDTO

class LessonMapper() {

    companion object {
        fun toDto(lesson: Lesson): LessonWithCompletionStatusDTO {
            return LessonWithCompletionStatusDTO(
                lessonId = lesson.lessonId,
                name = lesson.name,
                loginRequired = lesson.loginRequired
            )
        }

        fun toLessonStageCompletionDto(lessonStageWithoutElementsDTO: LessonStageWithoutElementsDTO, completed: Boolean?): LessonStageCompletionDTO {
            return LessonStageCompletionDTO(
                lessonStageId = lessonStageWithoutElementsDTO.lessonStageId,
                name = lessonStageWithoutElementsDTO.name,
                index = lessonStageWithoutElementsDTO.index,
                lessonId = lessonStageWithoutElementsDTO.lessonId,
                completed = completed
            )
        }

        fun toDto(lessonStageElement: LessonStageElement): LessonStageElementDTO {
            return LessonStageElementDTO(
                videoUrl = lessonStageElement.videoUrl,
                description = lessonStageElement.description
            )
        }

        fun toDto(lessonStage: LessonStage): LessonStageWithoutElementsDTO {
            return LessonStageWithoutElementsDTO(
                lessonId = lessonStage.lessonId,
                lessonStageId = lessonStage.lessonStageId,
                name = lessonStage.name,
                index = lessonStage.index
            )
        }
    }
}