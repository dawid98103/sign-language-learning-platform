package pl.dkobylarz.signlearning.domain.lesson.domain

import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("lesson_completed")
class LessonCompleted(
    val lessonId: Int,
    val completionDate: LocalDateTime
)