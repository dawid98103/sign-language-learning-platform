package pl.dkobylarz.signlearning.domain.lesson.dto

import java.math.BigDecimal

data class LessonStageWithoutElementsDto(
    val lessonStageId: Int,
    val name: String,
    val index: BigDecimal,
    val lessonId: Int
)
