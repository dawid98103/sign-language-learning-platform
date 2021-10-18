package pl.dkobylarz.signlearning.domain.lesson.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal

@Table("lesson_stage")
data class LessonStage(
    @Id
    val lessonStageId: Int,
    val name: String,
    val index: BigDecimal,
    val lessonId: Int,
    @MappedCollection(idColumn = "lesson_stage_id")
    val lessonStageElements: MutableSet<LessonStageElement>
)

