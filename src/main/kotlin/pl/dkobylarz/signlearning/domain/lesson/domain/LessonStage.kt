package pl.dkobylarz.signlearning.domain.lesson.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table

@Table("lesson_stage")
class LessonStage(
    @Id
    val lessonStageId: Int,
    val name: String,
    val index: String,
    @MappedCollection(idColumn = "lesson_stage_id")
    val lessonStages: Set<LessonStageElement>
)

