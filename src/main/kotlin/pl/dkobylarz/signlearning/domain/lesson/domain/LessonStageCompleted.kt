package pl.dkobylarz.signlearning.domain.lesson.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("lesson_stage_completed")
data class LessonStageCompleted(
    @Id
    val lessonStageCompletedId: Int,
    val lessonStageId: Int,
    val userId: Int,
    val completionDate: LocalDateTime
)