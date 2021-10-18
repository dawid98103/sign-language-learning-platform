package pl.dkobylarz.signlearning.domain.lesson.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table

@Table("lesson")
data class Lesson(
    @Id
    val lessonId: Int,
    val name: String,
    val loginRequired: Boolean,
    @MappedCollection(idColumn = "lesson_id")
    val lessonStages: Set<LessonStage>
) {
    fun getStagesForLesson(): Set<LessonStage> {
        return lessonStages
    }

    fun getLessonStageById(lessonStageId: Int): LessonStage {
        return lessonStages.asSequence()
            .filter { it.lessonStageId == lessonStageId}
            .first()
    }
}