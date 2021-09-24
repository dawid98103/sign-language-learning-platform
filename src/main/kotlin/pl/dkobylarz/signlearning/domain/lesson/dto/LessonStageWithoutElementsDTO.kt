package pl.dkobylarz.signlearning.domain.lesson.dto

import java.math.BigDecimal

data class LessonStageWithoutElementsDTO(
    val lessonStageId: Int,
    val name: String,
    val index: BigDecimal,
    val lessonId: Int
)
