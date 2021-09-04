package pl.dkobylarz.signlearning.domain.lesson.domain

import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageCompletionDto
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageWithoutElementsDto
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageElementDto
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonWithCompletionStatusDto

class LessonMapper() {

    companion object {
        fun toDto(lesson: Lesson): LessonWithCompletionStatusDto {
            return LessonWithCompletionStatusDto(
                lessonId = lesson.lessonId,
                name = lesson.name,
                loginRequired = lesson.loginRequired
            )
        }

        fun toLessonStageCompletionDto(lessonStageWithoutElementsDto: LessonStageWithoutElementsDto, completed: Boolean?): LessonStageCompletionDto {
            return LessonStageCompletionDto(
                lessonStageId = lessonStageWithoutElementsDto.lessonStageId,
                name = lessonStageWithoutElementsDto.name,
                index = lessonStageWithoutElementsDto.index,
                lessonId = lessonStageWithoutElementsDto.lessonId,
                completed = completed
            )
        }

        fun toDto(lessonStageElement: LessonStageElement): LessonStageElementDto {
            return LessonStageElementDto(
                videoUrl = lessonStageElement.videoUrl,
                description = lessonStageElement.description
            )
        }

        fun toDto(lessonStage: LessonStage): LessonStageWithoutElementsDto {
            return LessonStageWithoutElementsDto(
                lessonId = lessonStage.lessonId,
                lessonStageId = lessonStage.lessonStageId,
                name = lessonStage.name,
                index = lessonStage.index
            )
        }
    }
}