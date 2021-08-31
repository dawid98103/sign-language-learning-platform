package pl.dkobylarz.signlearning.domain.lesson.infrastructure

import pl.dkobylarz.signlearning.domain.lesson.domain.LessonStageCompleted

class LessonStageCompletedDatabaseAdapter(private val lessonStageCompletedRepository: LessonStageCompletedRepository) : LessonStageCompletedDatabase {

    override fun getCompletedLessonsForUser(userId: Int): List<LessonStageCompleted> {
        return lessonStageCompletedRepository.findAllByUserId(userId)
    }
}