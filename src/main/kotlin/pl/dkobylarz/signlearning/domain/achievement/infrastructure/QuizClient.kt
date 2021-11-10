package pl.dkobylarz.signlearning.domain.achievement.infrastructure

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.quiz.QuizFacade
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizDTO

@Service
@RequiredArgsConstructor
class QuizClient(private val quizFacade: QuizFacade) {

    fun getQuizzes(): Set<QuizDTO> {
        return quizFacade.getQuizzes()
    }
}