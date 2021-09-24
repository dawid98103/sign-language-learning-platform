package pl.dkobylarz.signlearning.domain.quiz.infrastructure

import pl.dkobylarz.signlearning.domain.quiz.domain.QuizQuestion
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizQuestionDTO

class QuizQuestionMapper {
    companion object {
        fun toDto(quizQuestion: QuizQuestion): QuizQuestionDTO {
            return QuizQuestionDTO(
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