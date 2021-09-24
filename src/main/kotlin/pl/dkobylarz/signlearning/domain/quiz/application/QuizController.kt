package pl.dkobylarz.signlearning.domain.quiz.application

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.dkobylarz.signlearning.domain.quiz.QuizFacade
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizDTO
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizQuestionDTO

@RestController
@RequiredArgsConstructor
@RequestMapping("/quizzes")
class QuizController(val quizFacade: QuizFacade) {

    @GetMapping("")
    fun getQuizzes(): ResponseEntity<Set<QuizDTO>> {
        return ResponseEntity.ok(quizFacade.getQuizzes())
    }

    @GetMapping("/{lessonId}/quiz")
    fun getQuizForLessonWithCompletionStatus(
        @PathVariable lessonId: Int
    ): ResponseEntity<QuizDTO> {
        return ResponseEntity.ok(quizFacade.getQuizForLessonWithCompletionStatus(lessonId))
    }

    @GetMapping("/{lessonId}/quiz/{quizId}/questions")
    fun getQuestionsForGivenQuizWithAnswers(
        @PathVariable lessonId: Int,
        @PathVariable quizId: Int
    ): ResponseEntity<Set<QuizQuestionDTO>> {
        return ResponseEntity.ok(quizFacade.getQuestionsForGivenQuizWithAnswers(quizId))
    }

    @GetMapping("/{lessonId}/quiz/{quizId}/question/{quizQuestionId}/answers/{answerId}")
    fun getIsTheCorrectAnswer(
        @PathVariable lessonId: Int,
        @PathVariable quizId: Int,
        @PathVariable quizQuestionId: Int,
        @PathVariable answerId: Int
    ): ResponseEntity<Boolean> {
        val isTheCorrectAnswer = quizFacade.getIsTheCorrectAnswer(quizId, quizQuestionId, answerId)
        return ResponseEntity.ok(isTheCorrectAnswer)
    }
}