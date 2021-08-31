package pl.dkobylarz.signlearning.domain.lesson.infrastructure

import org.springframework.data.repository.CrudRepository
import pl.dkobylarz.signlearning.domain.lesson.domain.LessonStageCompleted

interface LessonStageCompletedRepository : CrudRepository<LessonStageCompleted, Int> {

    fun findAllByUserId(userId: Int): List<LessonStageCompleted>
}