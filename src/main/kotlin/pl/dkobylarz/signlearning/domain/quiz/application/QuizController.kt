package pl.dkobylarz.signlearning.domain.quiz.application

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import pl.dkobylarz.signlearning.domain.quiz.QuizFacade
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizDTO
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizProcessDTO
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizQuestionDTO
import pl.dkobylarz.signlearning.domain.user.domain.User
import java.time.LocalDateTime

@RestController
@RequiredArgsConstructor
@RequestMapping("/quizzes")
class QuizController(val quizFacade: QuizFacade) {

    @GetMapping("/process/test")
    fun getProcessMap(): ResponseEntity<Map<Pair<Int, Int>, QuizProcessDTO>> {
        return ResponseEntity.ok(quizFacade.getQuizProcess())
    }

    @GetMapping("")
    fun getQuizzes(): ResponseEntity<Set<QuizDTO>> {
        return ResponseEntity.ok(quizFacade.getQuizzes())
    }

    @GetMapping("/user")
    fun hasActiveQuizzes(@AuthenticationPrincipal user: User): ResponseEntity<Boolean>{
        return ResponseEntity.ok(quizFacade.hasActiveQuizzes(user.userId!!))
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

    @PostMapping("/{lessonId}/quiz/{quizId}/start")
    fun startQuizForUser(@PathVariable lessonId: Int, @PathVariable quizId: Int, @AuthenticationPrincipal user: User): ResponseEntity<LocalDateTime> {
        quizFacade.startQuizForUser(user.userId!!, quizId)
        return ResponseEntity.ok(LocalDateTime.now())
    }

    @PostMapping("/{lessonId}/quiz/{quizId}/finish")
    fun finishQuizForUser(@PathVariable lessonId: Int, @PathVariable quizId: Int, @AuthenticationPrincipal user: User): ResponseEntity<LocalDateTime> {
        quizFacade.finishQuizForUser(user.userId!!, quizId)
        return ResponseEntity.ok(LocalDateTime.now())
    }

    @GetMapping("/{lessonId}/quiz/{quizId}/question/{quizQuestionId}/answers/{answerId}")
    fun processUserAnswer(
            @PathVariable lessonId: Int,
            @PathVariable quizId: Int,
            @PathVariable quizQuestionId: Int,
            @PathVariable answerId: Int
    ): ResponseEntity<Boolean> {
        val isTheCorrectAnswer = quizFacade.processUserAnswer(quizId, quizQuestionId, answerId)
        return ResponseEntity.ok(isTheCorrectAnswer)
    }
}