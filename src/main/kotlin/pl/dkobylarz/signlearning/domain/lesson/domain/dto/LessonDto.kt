package pl.dkobylarz.signlearning.domain.lesson.domain.dto

data class LessonDto(
    val lessonId: Int,
    val name: String,
    val lessonStages: Set<LessonStageDto>
)
