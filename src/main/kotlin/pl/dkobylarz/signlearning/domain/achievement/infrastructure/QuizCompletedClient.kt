package pl.dkobylarz.signlearning.domain.achievement.infrastructure

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.quizcompleted.QuizCompletedFacade
import pl.dkobylarz.signlearning.domain.quizcompleted.dto.QuizCompletedShortDTO

@Service
@RequiredArgsConstructor
class QuizCompletedClient(private val quizCompletedFacade: QuizCompletedFacade) {
    fun getCompletedQuizzesByUser(userId: Int): Set<QuizCompletedShortDTO> {
        return quizCompletedFacade.getCompletedQuizzesForUser(userId)
    }
}