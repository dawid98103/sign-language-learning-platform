package pl.dkobylarz.signlearning.domain.quiz.infrastructure

import pl.dkobylarz.signlearning.domain.quiz.domain.Quiz

class QuizDatabaseAdapter(private val quizRepository: QuizRepository) : QuizDatabase {

    override fun getQuizByLessonId(lessonId: Int): Quiz {
        return quizRepository.findByLessonId(lessonId).orElseThrow()
    }

    override fun getQuizzes(): Set<Quiz> {
        return quizRepository.findAll()
    }
}