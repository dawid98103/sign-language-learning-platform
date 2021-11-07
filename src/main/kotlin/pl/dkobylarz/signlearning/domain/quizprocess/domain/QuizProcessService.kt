package pl.dkobylarz.signlearning.domain.quizprocess.domain

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.quiz.domain.QuizQuestionService
import pl.dkobylarz.signlearning.domain.quizcompleted.QuizCompletedFacade
import pl.dkobylarz.signlearning.domain.quizprocess.dto.ActiveQuizProcessDTO
import pl.dkobylarz.signlearning.domain.quizprocess.dto.QuizProcessDTO
import pl.dkobylarz.signlearning.domain.user.domain.User
import java.time.LocalDateTime
import java.util.concurrent.ConcurrentHashMap
import java.util.logging.Logger

@Service
class QuizProcessService(
    private val quizCompletedFacade: QuizCompletedFacade,
    private val quizQuestionService: QuizQuestionService,
) {
    @Value("\${signlearning.job.quiz-timeout-in-minutes}")
    lateinit var quizTimeout: String
    private val quizProcess: ConcurrentHashMap<Pair<Int, Int>, QuizProcessDTO> = ConcurrentHashMap()

    companion object {
        val LOG = Logger.getLogger(QuizProcessService::class.java.name)
    }

    fun startQuiz(userId: Int, quizId: Int) {
        LOG.info("[QUIZ PROCESS]: Rozpoczęto quiz: ${quizId} dla użytkownika: ${userId} ")
        val quizUserPair = Pair(userId, quizId)
        quizProcess[quizUserPair] = QuizProcessDTO(
            HashMap(),
            LocalDateTime.now()
        )
    }

    fun finishQuizForUser(userId: Int, quizId: Int): LocalDateTime {
        val quizUserPair = Pair(userId, quizId)
        val quizProcessForUser = quizProcess[quizUserPair]
        LOG.info("[QUIZ PROCESS]: Zakończono quiz: ${quizId} dla użytkownika: ${userId}")
        quizCompletedFacade.markAsCompletedForUser(userId, quizId, quizProcessForUser!!.startDate, quizProcessForUser)
        quizProcess.remove(quizUserPair)
        return LocalDateTime.now()
    }

    fun terminateTimeoutQuizProcesses() {
        quizProcess.asSequence()
            .filter { it.value.startDate.plusMinutes(quizTimeout.toLong()).isBefore(LocalDateTime.now()) }
            .forEach {
                LOG.info("[QUIZ PROCESS]: AUTOMAT")
                finishQuizForUser(it.key.first, it.key.second)
            }
    }

    fun processUserAnswer(quizId: Int, quizQuestionId: Int, answerId: Int): Boolean {
        val answerCorrectness = quizQuestionService.isAnswerCorrect(quizId, quizQuestionId, answerId)
        val currentUser = SecurityContextHolder.getContext().authentication.principal as User
        process(currentUser.userId!!, quizId, quizQuestionId, answerCorrectness)
        return answerCorrectness
    }


    fun terminateActiveQuizz(userId: Int) {
        val activeQuizProcessDTO = getActiveQuizProcess(userId)
        activeQuizProcessDTO?.let {
            finishQuizForUser(userId, it.quizId)
        }
    }

    fun hasActiveQuizzes(userId: Int): Boolean {
        return quizProcess.asSequence()
            .map { it.key }
            .map { it.first }
            .filter { it == userId }
            .firstOrNull() !== null
    }

    fun getMapState(): Map<Pair<Int, Int>, QuizProcessDTO> {
        return quizProcess
    }


    private fun getActiveQuizProcess(userId: Int): ActiveQuizProcessDTO? {
        val activeQuizProcess = quizProcess.asSequence()
            .find { it.key.first == userId }

        return if (activeQuizProcess != null) {
            ActiveQuizProcessDTO(activeQuizProcess.key.second, activeQuizProcess.value.startDate)
        } else {
            null
        }
    }

    private fun process(userId: Int, quizId: Int, quizQuestionId: Int, answerCorrectness: Boolean) {
        val quizUserPair = Pair(userId, quizId)
        val quizQuestion = quizQuestionService.getQuizQuestionForQuizQuestionId(quizId, quizQuestionId)
        val currentQuizProcess = quizProcess[quizUserPair]

        currentQuizProcess!!.userAnswers[quizQuestion] = answerCorrectness
        quizProcess[quizUserPair] = currentQuizProcess
    }
}