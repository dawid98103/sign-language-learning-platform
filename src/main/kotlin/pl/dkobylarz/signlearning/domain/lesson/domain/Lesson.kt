package pl.dkobylarz.signlearning.domain.lesson.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table
import pl.dkobylarz.signlearning.domain.quiz.domain.Quiz

@Table("lesson")
data class Lesson(
    @Id
    val lessonId: Int,
    val name: String,
    val loginRequired: Boolean,
    @MappedCollection(idColumn = "lesson_id")
    val lessonStages: Set<LessonStage>,
    @MappedCollection(idColumn = "quiz_id")
    val quiz: Quiz
)