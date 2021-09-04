package pl.dkobylarz.signlearning.domain.lesson.dto

data class LessonWithCompletionStatusDto(
    val lessonId: Int,
    val name: String,
    val loginRequired: Boolean
)
