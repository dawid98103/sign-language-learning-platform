package pl.dkobylarz.signlearning.domain.lesson.domain

import pl.dkobylarz.signlearning.domain.lesson.domain.dto.LessonWithCompletitionStatusDto
import pl.dkobylarz.signlearning.domain.lesson.domain.dto.LessonStageCompletedDto
import pl.dkobylarz.signlearning.domain.lesson.domain.dto.LessonStageDto
import pl.dkobylarz.signlearning.domain.lesson.domain.dto.LessonStageElementDto
import java.util.stream.Collectors

class LessonMapper() {

    companion object {
        fun toDto(lesson: Lesson): LessonWithCompletitionStatusDto {
            return LessonWithCompletitionStatusDto(
                lessonId = lesson.lessonId,
                name = lesson.name,
                lessonStages = lesson.lessonStages.stream()
                    .map { toDto(it) }.collect(Collectors.toSet()),
                loginRequired = lesson.loginRequired
            )
        }

        fun toDto(lessonStage: LessonStage): LessonStageDto {
            return LessonStageDto(
                lessonStageId = lessonStage.lessonStageId,
                name = lessonStage.name,
                index = lessonStage.index,
            )
        }

        fun toDto(lessonStageElement: LessonStageElement): LessonStageElementDto {
            return LessonStageElementDto(
                lessonStageElementId = lessonStageElement.lessonStageElementId,
                videoUrl = lessonStageElement.videoUrl,
                description = lessonStageElement.description
            )
        }

        fun toDto(lessonStageCompleted: LessonStageCompleted): LessonStageCompletedDto {
            return LessonStageCompletedDto(
                lessonStageId = lessonStageCompleted.lessonStageId,
                userId = lessonStageCompleted.userId,
                completionDate = lessonStageCompleted.completionDate
            )
        }
    }
}