package pl.dkobylarz.signlearning.domain.quiz.infrastructure

import pl.dkobylarz.signlearning.domain.quiz.domain.QuizQuestion
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizQuestionDto

class QuizQuestionMapper {
    companion object {
        fun toDto(quizQuestion: QuizQuestion): QuizQuestionDto {
            return QuizQuestionDto(
                quizQuestionId = quizQuestion.quizQuestionId,
                questionNumber = quizQuestion.questionNumber,
                questionName = quizQuestion.questionName,
                points = quizQuestion.points,
                videoUrl = quizQuestion.videoUrl,
                answers = quizQuestion.answers.asSequence().map { QuizAnswerMapper.toWithoutCorrectDto(it) }.toSet()
            )
        }
    }
}