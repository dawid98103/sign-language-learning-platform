package pl.dkobylarz.signlearning.domain.quiz.domain

import pl.dkobylarz.signlearning.domain.quiz.dto.QuizQuestionDTO
import pl.dkobylarz.signlearning.domain.quiz.infrastructure.QuizQuestionDatabase
import pl.dkobylarz.signlearning.domain.quiz.infrastructure.QuizQuestionMapper

class QuizQuestionService(private val quizQuestionDatabase: QuizQuestionDatabase) {

    fun getQuestionsForGivenQuizWithAnswers(quizId: Int): Set<QuizQuestionDTO> {
        val questionsForQuiz: Set<QuizQuestion> = quizQuestionDatabase.getQuestionsForQuiz(quizId)
        return questionsForQuiz.asSequence()
                .map { QuizQuestionMapper.toDto(it) }
                .toSet()
    }

    fun getQuizQuestionForQuizQuestionId(quizQuestionId: Int): QuizQuestionDTO {
        return QuizQuestionMapper.toDto(quizQuestionDatabase.getQuestionByQuizQuestionId(quizQuestionId))
    }

    fun isAnswerCorrect(quizId: Int, quizQuestionId: Int, answerId: Int): Boolean {
        val questionsForQuiz: Set<QuizQuestion> = quizQuestionDatabase.getQuestionsForQuiz(quizId)
        return questionsForQuiz.asSequence()
                .filter { it.quizQuestionId == quizQuestionId }
                .first().answers.asSequence()
                .filter { it.correct }
                .first().quizAnswerId == answerId
    }
}