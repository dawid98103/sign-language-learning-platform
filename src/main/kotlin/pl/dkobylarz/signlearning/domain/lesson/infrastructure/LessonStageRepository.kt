package pl.dkobylarz.signlearning.domain.lesson.infrastructure

import org.springframework.data.repository.CrudRepository
import pl.dkobylarz.signlearning.domain.lesson.domain.LessonStage

interface LessonStageRepository : CrudRepository<LessonStage, Int> {
}