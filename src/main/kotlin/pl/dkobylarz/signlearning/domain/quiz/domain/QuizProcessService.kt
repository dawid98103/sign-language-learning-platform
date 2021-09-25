package pl.dkobylarz.signlearning.domain.quiz.domain

import org.springframework.security.core.context.SecurityContextHolder
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizProcessDTO
import pl.dkobylarz.signlearning.domain.user.domain.User
import java.time.LocalDateTime
import java.util.concurrent.ConcurrentHashMap

class QuizProcessService(
        private val quizProcess: ConcurrentHashMap<Pair<Int, Int>, QuizProcessDTO>,
        private val quizCompletedService: QuizCompletedService,
        private val quizQuestionService: QuizQuestionService) {

    fun getMapState(): Map<Pair<Int, Int>, QuizProcessDTO>{
        return quizProcess
    }

    fun processUserAnswer(quizId: Int, quizQuestionId: Int, answerId: Int): Boolean {
        val answerCorrectness = quizQuestionService.isAnswerCorrect(quizId, quizQuestionId, answerId)
        val currentUser = SecurityContextHolder.getContext().authentication.principal as User
        process(currentUser.userId!!, quizId, quizQuestionId, answerCorrectness)
        return answerCorrectness
    }

    fun process(userId: Int, quizId: Int, quizQuestionId: Int, answer: Boolean) {
        val quizUserPair = Pair(userId, quizId)
        val quizQuestion = quizQuestionService.getQuizQuestionForQuizQuestionId(quizQuestionId)
        val currentQuizProcess = quizProcess[quizUserPair]

        currentQuizProcess!!.userAnswers[quizQuestion] = answer
        quizProcess[quizUserPair] = currentQuizProcess
    }

    fun startQuiz(userId: Int, quizId: Int) {
        val quizUserPair = Pair(userId, quizId)
        quizProcess[quizUserPair] = QuizProcessDTO(
                HashMap(),
                LocalDateTime.now()
        )
    }

    fun finishQuiz(userId: Int, quizId: Int) {
        val quizUserPair = Pair(userId, quizId)
        val quizProcessForUser = quizProcess[quizUserPair]
        quizCompletedService.finishQuizForUser(userId, quizId, quizProcessForUser!!)
        quizProcess.remove(quizUserPair)
    }

    fun hasActiveQuizzes(userId: Int): Boolean {
        return quizProcess.asSequence()
                .map { it.key }
                .map { it.first }
                .filter { it == userId }
                .firstOrNull() !== null
    }
}