package pl.dkobylarz.signlearning.domain.lesson.domain

import org.springframework.data.relational.core.mapping.Table

@Table("lesson_stage_element")
data class LessonStageElement(
    val videoUrl: String,
    val description: String,
)