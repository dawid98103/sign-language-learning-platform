package pl.dkobylarz.signlearning.domain.quiz.infrastructure

import pl.dkobylarz.signlearning.domain.quiz.domain.Quiz
import java.util.concurrent.ConcurrentHashMap

class QuizDatabaseInMemoryAdapter : QuizDatabase {

    lateinit var quizTable: ConcurrentHashMap<Int, Quiz>

    override fun getQuizByLessonId(lessonId: Int): Quiz {
        return quizTable.values.find { it.lessonId == lessonId }!!
    }

    override fun getQuizzes(): Set<Quiz> {
        return quizTable.values.toSet()
    }
}