package pl.dkobylarz.signlearning.domain.lesson.domain.dto

data class LessonWithCompletitionStatusDto(
    val lessonId: Int,
    val name: String,
    val lessonStages: Set<LessonStageDto>,
    val loginRequired: Boolean
)
