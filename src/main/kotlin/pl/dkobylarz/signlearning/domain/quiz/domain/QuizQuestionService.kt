package pl.dkobylarz.signlearning.domain.quiz.domain

import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizQuestionDTO
import pl.dkobylarz.signlearning.domain.quiz.infrastructure.QuizRepository

@Service
class QuizQuestionService(private val quizRepository: QuizRepository) {

    fun getQuestionsForGivenQuizWithAnswers(quizId: Int): Set<QuizQuestionDTO> {
        val quiz: Quiz = quizRepository.findByQuizId(quizId)
        val questionsForQuiz: Set<QuizQuestion> = quiz.getQuestionsForQuiz()

        return questionsForQuiz.asSequence()
            .map { QuizQuestionMapper.toDto(it) }
            .toSet()
    }

    fun getQuizQuestionForQuizQuestionId(quizId: Int, quizQuestionId: Int): QuizQuestionDTO {
        val quiz: Quiz = quizRepository.findByQuizId(quizId)
        return QuizQuestionMapper.toDto(quiz.getQuizQuestionIdById(quizQuestionId))
    }

    fun isAnswerCorrect(quizId: Int, quizQuestionId: Int, answerId: Int): Boolean {
        val quiz: Quiz = quizRepository.findByQuizId(quizId)
        val questionsForQuiz: Set<QuizQuestion> = quiz.getQuestionsForQuiz()
        return questionsForQuiz.asSequence()
            .filter { it.quizQuestionId == quizQuestionId }
            .first().answers.asSequence()
            .filter { it.correct }
            .first().quizAnswerId == answerId
    }
}