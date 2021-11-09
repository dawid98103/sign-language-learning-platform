package pl.dkobylarz.signlearning.domain.quiz

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.dkobylarz.signlearning.domain.quiz.domain.Quiz
import pl.dkobylarz.signlearning.domain.quiz.domain.QuizQuestionService
import pl.dkobylarz.signlearning.domain.quiz.domain.QuizService
import pl.dkobylarz.signlearning.domain.quiz.dto.*
import pl.dkobylarz.signlearning.domain.user.domain.User

@Service
@Transactional
class QuizFacade(
    private val quizService: QuizService,
    private val quizQuestionService: QuizQuestionService
) {

    fun getQuiz(quizId: Int): Quiz {
        return quizService.getQuiz(quizId)
    }

    fun getQuizForLessonWithCompletionStatus(lessonId: Int, user: User): QuizCompletionStatusDTO {
        return quizService.getQuizForLessonWithCompletionStatus(lessonId, user)
    }

    fun getQuestionsForGivenQuizWithAnswers(quizId: Int): Set<QuizQuestionDTO> {
        return quizQuestionService.getQuestionsForGivenQuizWithAnswers(quizId)
    }

    fun getQuizzes(): Set<QuizDTO> {
        return quizService.getQuizzes()
    }

    fun getQuizzesWithCompletionStatus(userId: Int): Set<QuizCompletionStatusDTO> {
        return quizService.getQuizzesWithCompletionStatus(userId)
    }

    fun getQuizResult(quizId: Int, lessonId: Int, userId: Int): QuizResultDTO? {
        return quizService.getQuizResult(quizId, lessonId, userId)
    }

    fun getQuizzesWithCompletionStatusForUser(user: User): Set<QuizCompletedResultShortDTO> {
        return quizService.getQuizzesWithCompletionStatusForUser(user)
    }
}