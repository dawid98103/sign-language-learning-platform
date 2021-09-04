package pl.dkobylarz.signlearning.domain.lesson.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal

@Table("lesson_stage")
class LessonStage(
    @Id
    val lessonStageId: Int,
    val name: String,
    val index: BigDecimal,
    val lessonId: Int,
    val lessonStageElements: MutableSet<LessonStageElement>
) {
    fun addStageElement(lessonStageElement: LessonStageElement) {
        this.lessonStageElements.add(lessonStageElement)
    }
}

