package pl.dkobylarz.signlearning.domain.lesson.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table

@Table("lesson")
class Lesson(
    @Id
    @Column("id")
    val lessonId: Int,
    val lessonGroupId: Int,
    val lessonName: String,
    @MappedCollection(keyColumn = "LESSON_ID", idColumn = "LESSON_ID")
    val lessonStages: Set<LessonStage>
)