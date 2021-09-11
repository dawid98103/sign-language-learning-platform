package pl.dkobylarz.signlearning.domain.quiz.domain

import pl.dkobylarz.signlearning.domain.quiz.dto.QuizDto
import pl.dkobylarz.signlearning.domain.quiz.infrastructure.QuizDatabase
import pl.dkobylarz.signlearning.domain.quiz.infrastructure.QuizMapper

class QuizService(private val quizDatabase: QuizDatabase) {

    fun getQuizForLessonWithCompletionStatus(lessonId: Int): QuizDto {
        val quizForLesson: Quiz = quizDatabase.getQuizByLessonId(lessonId)
        return QuizMapper.toDto(quizForLesson)
    }

    fun getQuizzes(): Set<QuizDto> {
        val quizzes: Set<Quiz> = quizDatabase.getQuizzes()
        return quizzes.asSequence()
            .map { QuizMapper.toDto(it) }
            .toSet()
    }
}