package pl.dkobylarz.signlearning.domain.lesson.dto

data class LessonWithCompletionStatusDTO(
    val lessonId: Int,
    val name: String,
    val loginRequired: Boolean
)
