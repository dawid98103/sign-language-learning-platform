package pl.dkobylarz.signlearning.domain.lesson.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("lesson_stage_element")
class LessonStageElement(
    @Id
    val lessonStageElementId: Int,
    val lessonStageId: Int,
    val videoUrl: String,
    val description: String,
)