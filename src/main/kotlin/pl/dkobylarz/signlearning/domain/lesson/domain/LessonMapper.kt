package pl.dkobylarz.signlearning.domain.lesson.domain

import pl.dkobylarz.signlearning.domain.lesson.dto.*

class LessonMapper() {

    companion object {
        fun toLessonStageDto(lessonStage: LessonStage): LessonStageDTO {
            return LessonStageDTO(
                lessonStageId = lessonStage.lessonStageId,
                name = lessonStage.name,
                lessonId = lessonStage.lessonId
            )
        }

        fun toDto(lesson: Lesson): LessonWithCompletionStatusDTO {
            return LessonWithCompletionStatusDTO(
                lessonId = lesson.lessonId,
                name = lesson.name,
                loginRequired = lesson.loginRequired,
                completed = false
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

        fun toLessonStageCompletionDto(
            lessonStageWithoutElementsDTO: LessonStageWithoutElementsDTO,
            completed: Boolean?
        ): LessonStageCompletionDTO {
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
    }
}