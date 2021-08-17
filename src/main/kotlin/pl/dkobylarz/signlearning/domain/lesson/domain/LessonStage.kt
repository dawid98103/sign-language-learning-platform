package pl.dkobylarz.signlearning.domain.lesson.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import javax.validation.constraints.Size

@Table("lesson_stage")
class LessonStage(
    @Id
    val lessonStageId: Int,
    val lessonId: Int,
    val stageName: String,
    val stageNumber: Int,
    @Size(max = 2083, message = "url should have max 2083 chars!")
    val videoUrl: String
)

