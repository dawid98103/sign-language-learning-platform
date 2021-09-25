package pl.dkobylarz.signlearning.domain.quiz

import org.springframework.transaction.annotation.Transactional
import pl.dkobylarz.signlearning.domain.quiz.domain.QuizProcessService
import pl.dkobylarz.signlearning.domain.quiz.domain.QuizQuestionService
import pl.dkobylarz.signlearning.domain.quiz.domain.QuizService
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizDTO
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizProcessDTO
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizQuestionDTO

@Transactional
class QuizFacade(
        private val quizService: QuizService,
        private val quizProcessService: QuizProcessService,
        private val quizQuestionService: QuizQuestionService
) {

    fun getQuizForLessonWithCompletionStatus(lessonId: Int): QuizDTO {
        return quizService.getQuizForLessonWithCompletionStatus(lessonId)
    }

    fun getQuizzes(): Set<QuizDTO> {
        return quizService.getQuizzes()
    }

    fun getQuestionsForGivenQuizWithAnswers(quizId: Int): Set<QuizQuestionDTO> {
        return quizQuestionService.getQuestionsForGivenQuizWithAnswers(quizId)
    }

    fun processUserAnswer(quizId: Int, quizQuestionId: Int, answerId: Int): Boolean {
        return quizProcessService.processUserAnswer(quizId, quizQuestionId, answerId)
    }

    fun getQuizProcess(): Map<Pair<Int, Int>, QuizProcessDTO>{
        return quizProcessService.getMapState()
    }

    fun hasActiveQuizzes(userId: Int): Boolean {
        return quizProcessService.hasActiveQuizzes(userId)
    }

    fun startQuizForUser(userId: Int, quizId: Int) {
        quizProcessService.startQuiz(userId, quizId)
    }

    fun finishQuizForUser(userId: Int, quizId: Int) {
        quizProcessService.finishQuiz(userId, quizId)
    }
}