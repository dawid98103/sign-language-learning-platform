package pl.dkobylarz.signlearning.domain.quizprocess

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class QuizProcessWorker(private val quizProcessFacade: QuizProcessFacade) {

    @Scheduled(fixedDelay = 1000)
    fun checkActiveQuizProcesses() {
        quizProcessFacade.terminateTimeoutQuizProcesses();
    }
}