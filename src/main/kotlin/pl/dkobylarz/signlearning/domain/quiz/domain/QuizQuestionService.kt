package pl.dkobylarz.signlearning.domain.quiz.domain

import pl.dkobylarz.signlearning.domain.quiz.dto.QuizQuestionDto
import pl.dkobylarz.signlearning.domain.quiz.infrastructure.QuizQuestionDatabase
import pl.dkobylarz.signlearning.domain.quiz.infrastructure.QuizQuestionMapper

class QuizQuestionService(private val quizQuestionDatabase: QuizQuestionDatabase) {

    fun getQuestionsForGivenQuizWithAnswers(quizId: Int): Set<QuizQuestionDto> {
        val questionsForQuiz: Set<QuizQuestion> = quizQuestionDatabase.getQuestionsForQuiz(quizId)
        return questionsForQuiz.asSequence()
            .map { QuizQuestionMapper.toDto(it) }
            .toSet()
    }
}