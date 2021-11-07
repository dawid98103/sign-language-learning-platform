package pl.dkobylarz.signlearning.domain.quizprocess

import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.quizprocess.domain.QuizProcessService
import pl.dkobylarz.signlearning.domain.quizprocess.dto.ActiveQuizProcessDTO
import pl.dkobylarz.signlearning.domain.quizprocess.dto.QuizProcessDTO
import java.time.LocalDateTime

@Service
class QuizProcessFacade(private val quizProcessService: QuizProcessService) {

    fun terminateTimeoutQuizProcesses() {
        quizProcessService.terminateTimeoutQuizProcesses()
    }

    fun getQuizProcess(): Map<Pair<Int, Int>, QuizProcessDTO> {
        return quizProcessService.getMapState()
    }

    fun processUserAnswer(quizId: Int, quizQuestionId: Int, answerId: Int): Boolean {
        return quizProcessService.processUserAnswer(quizId, quizQuestionId, answerId)
    }

    fun startQuizForUser(userId: Int, quizId: Int) {
        quizProcessService.startQuiz(userId, quizId)
    }

    fun finishQuizForUser(userId: Int, quizId: Int): LocalDateTime {
        return quizProcessService.finishQuizForUser(userId, quizId)
    }

    fun hasActiveQuizzes(userId: Int): Boolean {
        return quizProcessService.hasActiveQuizzes(userId)
    }

    fun terminateActiveQuiz(userId: Int) {
        quizProcessService.terminateActiveQuizz(userId)
    }
}