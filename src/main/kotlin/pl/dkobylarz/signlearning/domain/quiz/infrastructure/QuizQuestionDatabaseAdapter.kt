package pl.dkobylarz.signlearning.domain.quiz.infrastructure

import pl.dkobylarz.signlearning.domain.quiz.domain.QuizQuestion

class QuizQuestionDatabaseAdapter(private val quizQuestionRepository: QuizQuestionRepository) : QuizQuestionDatabase {

    override fun getQuestionsForQuiz(quizId: Int): Set<QuizQuestion> {
        return quizQuestionRepository.findByQuizId(quizId)
    }
}