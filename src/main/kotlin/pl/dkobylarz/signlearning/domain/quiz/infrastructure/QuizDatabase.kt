package pl.dkobylarz.signlearning.domain.quiz.infrastructure

import pl.dkobylarz.signlearning.domain.quiz.domain.Quiz

interface QuizDatabase {
    fun getQuizByLessonId(lessonId: Int): Quiz
    fun getQuizzes(): Set<Quiz>
}