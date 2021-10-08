package pl.dkobylarz.signlearning.domain.quiz.infrastructure

import pl.dkobylarz.signlearning.domain.quiz.domain.QuizQuestion
import pl.dkobylarz.signlearning.domain.quiz.exception.QuizQuestionNotFoundException

class QuizQuestionDatabaseAdapter(private val quizQuestionRepository: QuizQuestionRepository) : QuizQuestionDatabase {

    override fun getQuestionsForQuiz(quizId: Int): Set<QuizQuestion> {
        return quizQuestionRepository.findByQuizId(quizId)
    }

    override fun getQuestionByQuizQuestionId(quizQuestionId: Int): QuizQuestion {
        return quizQuestionRepository.findById(quizQuestionId).orElseThrow { QuizQuestionNotFoundException() }
    }
}
