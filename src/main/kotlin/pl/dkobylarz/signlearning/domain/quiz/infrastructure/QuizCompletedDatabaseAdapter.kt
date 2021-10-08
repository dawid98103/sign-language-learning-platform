package pl.dkobylarz.signlearning.domain.quiz.infrastructure

import pl.dkobylarz.signlearning.domain.quiz.domain.QuizCompleted

class QuizCompletedDatabaseAdapter(private val quizCompletedRepository: QuizCompletedRepository) : QuizCompletedDatabase {

    override fun save(quizCompleted: QuizCompleted) {
        quizCompletedRepository.save(quizCompleted)
    }


}