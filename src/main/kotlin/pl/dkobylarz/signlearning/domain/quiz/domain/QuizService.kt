package pl.dkobylarz.signlearning.domain.quiz.domain

import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizCompletedResultShortDTO
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizCompletionStatusDTO
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizDTO
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizResultDTO
import pl.dkobylarz.signlearning.domain.quiz.infrastructure.QuizRepository
import pl.dkobylarz.signlearning.domain.quizcompleted.QuizCompletedFacade
import pl.dkobylarz.signlearning.domain.quizcompleted.dto.QuizCompletedShortDTO
import pl.dkobylarz.signlearning.domain.user.domain.User

@Service
class QuizService(
    private val quizRepository: QuizRepository,
    private val quizCompletedFacade: QuizCompletedFacade
) {

    fun getQuiz(quizId: Int): Quiz {
        return quizRepository.findByQuizId(quizId)
    }

    fun getQuizzes(): Set<QuizDTO> {
        val quizzes: Set<Quiz> = quizRepository.findAll()
        return quizzes.asSequence()
            .map { QuizMapper.toDto(it) }
            .toSet()
    }

    fun getQuizzesWithCompletionStatus(userId: Int): Set<QuizCompletionStatusDTO> {
        val quizzes: Set<Quiz> = quizRepository.findAll()
        return quizzes.asSequence()
            .map {
                val quizResultDTO: QuizResultDTO? = quizCompletedFacade.getQuizResult(it, userId)
                QuizMapper.toDto(it, quizResultDTO)
            }
            .toSet()
    }

    fun getQuizForLessonWithCompletionStatus(lessonId: Int, user: User): QuizCompletionStatusDTO {
        val quizForLesson: Quiz = quizRepository.findByLessonId(lessonId)
        val quizResultDTO: QuizResultDTO? = quizCompletedFacade.getQuizResult(quizForLesson, user.userId!!)
        return QuizMapper.toDto(quizForLesson, quizResultDTO)
    }

    fun getQuizResult(quizId: Int, lessonId: Int, userId: Int): QuizResultDTO? {
        val quizForLesson: Quiz = quizRepository.findByLessonId(lessonId)
        return quizCompletedFacade.getQuizResult(quizForLesson, userId)
    }

    fun getQuizzesWithCompletionStatusForUser(user: User): Set<QuizCompletedResultShortDTO> {
        val completedQuizzesForUser = quizCompletedFacade.getCompletedQuizzesForUser(user.userId!!)
        val allQuizzes = quizRepository.findAll()
        return allQuizzes.asSequence()
            .map {
                if (userCompletedGivenQuiz(it.quizId, completedQuizzesForUser)) {
                    QuizMapper.toDto(it, true)
                } else {
                    QuizMapper.toDto(it, false)
                }
            }.toSet()
    }

    private fun userCompletedGivenQuiz(quizId: Int, completedQuizzesForUser: Set<QuizCompletedShortDTO>): Boolean {
        return completedQuizzesForUser.find { it.quizId == quizId } != null
    }
}