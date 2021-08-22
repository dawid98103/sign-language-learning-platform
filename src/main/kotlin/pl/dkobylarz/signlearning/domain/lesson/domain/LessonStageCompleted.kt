package pl.dkobylarz.signlearning.domain.lesson.domain

import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("lesson_stage_completed")
class LessonStageCompleted(
    val lessonStageId: Int,
    val completionDate: LocalDateTime
)