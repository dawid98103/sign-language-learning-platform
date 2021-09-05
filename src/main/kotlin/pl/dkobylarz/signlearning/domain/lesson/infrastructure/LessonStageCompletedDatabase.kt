package pl.dkobylarz.signlearning.domain.lesson.infrastructure

import pl.dkobylarz.signlearning.domain.lesson.domain.LessonStageCompleted

interface LessonStageCompletedDatabase {
    fun save(lessonStageCompleted: LessonStageCompleted): LessonStageCompleted
    fun existsByStageIdAndUserId(userId: Int, stageId:Int): Boolean
    fun getCompletedLessonsForUser(userId: Int): List<LessonStageCompleted>
}