package pl.dkobylarz.signlearning.domain.lesson.infrastructure

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import pl.dkobylarz.signlearning.domain.lesson.domain.LessonStage
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageWithoutElementsDto

interface LessonStageRepository : CrudRepository<LessonStage, Int> {

    fun findByLessonId(@Param("lessonId") lessonId: Int): List<LessonStageWithoutElementsDto>
}