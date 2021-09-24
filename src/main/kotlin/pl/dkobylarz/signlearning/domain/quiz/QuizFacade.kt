package pl.dkobylarz.signlearning.domain.quiz

import org.springframework.transaction.annotation.Transactional
import pl.dkobylarz.signlearning.domain.quiz.domain.QuizQuestionService
import pl.dkobylarz.signlearning.domain.quiz.domain.QuizService
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizDTO
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizQuestionDTO

@Transactional
class QuizFacade(
    private val quizService: QuizService,
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

    fun getIsTheCorrectAnswer(quizId: Int, quizQuestionId: Int, answerId: Int): Boolean {
        return quizQuestionService.getIsTheCorrectAnswer(quizId,quizQuestionId ,answerId)
    }
}