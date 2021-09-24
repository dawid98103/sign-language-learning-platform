package pl.dkobylarz.signlearning.domain.lesson.dto

import java.math.BigDecimal

class LessonStageCompletionDTO (
    val lessonStageId: Int,
    val name: String,
    val index: BigDecimal,
    val lessonId: Int,
    val completed: Boolean? = false
)