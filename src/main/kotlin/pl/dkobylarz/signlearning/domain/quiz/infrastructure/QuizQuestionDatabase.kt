package pl.dkobylarz.signlearning.domain.quiz.infrastructure

import pl.dkobylarz.signlearning.domain.quiz.domain.QuizQuestion

interface QuizQuestionDatabase {
    fun getQuestionsForQuiz(quizId: Int): Set<QuizQuestion>
    fun getQuestionByQuizQuestionId(quizQuestionId: Int): QuizQuestion
}