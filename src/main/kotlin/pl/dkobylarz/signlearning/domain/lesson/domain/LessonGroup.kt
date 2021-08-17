package pl.dkobylarz.signlearning.domain.lesson.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("lesson_group")
class LessonGroup(
    @Id
    val lessonGroupId: Int,
    val lessonGroupName: String,
    val accessLevel: Int
)