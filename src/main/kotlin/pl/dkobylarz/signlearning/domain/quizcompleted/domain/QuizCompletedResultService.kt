package pl.dkobylarz.signlearning.domain.quizcompleted.domain

import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.quiz.domain.Quiz
import pl.dkobylarz.signlearning.domain.quiz.domain.QuizQuestion
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizResultDTO
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizResultEntryDTO
import pl.dkobylarz.signlearning.domain.quizcompleted.infrastructure.QuizCompletedRepository

@Service
class QuizCompletedResultService(
    private val quizCompletedRepository: QuizCompletedRepository,
) {

    fun getQuizResult(quiz: Quiz, userId: Int): QuizResultDTO? {
        val quizResult: QuizCompleted? = quizCompletedRepository.findByQuizIdAndUserId(quiz.quizId, userId)
        if (quizResult != null) {
            val quizResultEntries = getQuizResultEntries(quiz)
            return QuizResultDTO(
                quiz.title,
                quiz.pointsToGain,
                quizResult.result,
                quizResultEntries,
                quizResult.startDate,
                quizResult.completionDate,
            )
        }
        return null
    }

    private fun getQuizResultEntries(quiz: Quiz): Set<QuizResultEntryDTO> {
        val quizQuestions: Set<QuizQuestion> = quiz.questions
        return quizQuestions.asSequence()
            .map {
                QuizResultEntryDTO(
                    it.questionNumber,
                    it.questionName,
                    it.answers.asSequence()
                        .filter { it.correct }
                        .first()
                        .description
                )
            }.toSet()
    }
}