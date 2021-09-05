package pl.dkobylarz.signlearning.domain.lesson.infrastructure

import pl.dkobylarz.signlearning.domain.lesson.domain.LessonStageCompleted

class LessonStageCompletedDatabaseAdapter(private val lessonStageCompletedRepository: LessonStageCompletedRepository) :
    LessonStageCompletedDatabase {

    override fun save(lessonStageCompleted: LessonStageCompleted): LessonStageCompleted {
        return lessonStageCompletedRepository.save(lessonStageCompleted)
    }

    override fun existsByStageIdAndUserId(userId: Int, stageId: Int): Boolean {
        return lessonStageCompletedRepository.existsByUserIdAndLessonStageId(userId, stageId)
    }

    override fun getCompletedLessonsForUser(userId: Int): List<LessonStageCompleted> {
        return lessonStageCompletedRepository.findAllByUserId(userId)
    }
}