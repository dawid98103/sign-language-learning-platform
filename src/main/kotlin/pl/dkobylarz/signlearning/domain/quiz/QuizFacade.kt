package pl.dkobylarz.signlearning.domain.quiz

import org.springframework.transaction.annotation.Transactional
import pl.dkobylarz.signlearning.domain.quiz.domain.QuizService
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizDto

@Transactional
class QuizFacade(private val quizService: QuizService) {

    fun getQuizForLessonWithCompletionStatus(lessonId: Int): QuizDto {
        return quizService.getQuizForLessonWithCompletionStatus(lessonId)
    }

    fun getQuizzes(): Set<QuizDto> {
        return quizService.getQuizzes()
    }
}