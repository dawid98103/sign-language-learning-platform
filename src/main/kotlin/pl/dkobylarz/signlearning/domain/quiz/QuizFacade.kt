package pl.dkobylarz.signlearning.domain.quiz

import org.springframework.transaction.annotation.Transactional
import pl.dkobylarz.signlearning.domain.quiz.domain.QuizQuestionService
import pl.dkobylarz.signlearning.domain.quiz.domain.QuizService
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizDto
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizQuestionDto

@Transactional
class QuizFacade(
    private val quizService: QuizService,
    private val quizQuestionService: QuizQuestionService
) {

    fun getQuizForLessonWithCompletionStatus(lessonId: Int): QuizDto {
        return quizService.getQuizForLessonWithCompletionStatus(lessonId)
    }

    fun getQuizzes(): Set<QuizDto> {
        return quizService.getQuizzes()
    }

    fun getQuestionsForGivenQuizWithAnswers(quizId: Int): Set<QuizQuestionDto> {
        return quizQuestionService.getQuestionsForGivenQuizWithAnswers(quizId)
    }
}