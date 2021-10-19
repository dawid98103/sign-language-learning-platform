package pl.dkobylarz.signlearning.domain.quizprocess.application

import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import pl.dkobylarz.signlearning.domain.quizprocess.QuizProcessFacade
import pl.dkobylarz.signlearning.domain.user.domain.User
import java.time.LocalDateTime

@RestController
@RequiredArgsConstructor
@RequestMapping("/quizzes/process")
class QuizProcessController(private val quizProcessFacade: QuizProcessFacade) {

    @PostMapping("/quiz/{quizId}/start")
    fun startQuizForUser(
        @PathVariable quizId: Int,
        @AuthenticationPrincipal user: User
    ): ResponseEntity<LocalDateTime> {
        quizProcessFacade.startQuizForUser(user.userId!!, quizId)
        return ResponseEntity.ok(LocalDateTime.now())
    }

    @PostMapping("/quiz/{quizId}/finish")
    fun finishQuizForUser(
        @PathVariable quizId: Int,
        @AuthenticationPrincipal user: User
    ): ResponseEntity<LocalDateTime> {
        return ResponseEntity.ok(quizProcessFacade.finishQuizForUser(user.userId!!, quizId))
    }

    @GetMapping("/quiz/{quizId}/question/{quizQuestionId}/answers/{answerId}")
    fun processUserAnswer(
        @PathVariable quizId: Int,
        @PathVariable quizQuestionId: Int,
        @PathVariable answerId: Int
    ): ResponseEntity<Boolean> {
        val isTheCorrectAnswer = quizProcessFacade.processUserAnswer(quizId, quizQuestionId, answerId)
        return ResponseEntity.ok(isTheCorrectAnswer)
    }

    @PostMapping("/quiz/terminate")
    fun terminateActiveQuizzesForUser(
        @AuthenticationPrincipal user: User
    ): ResponseEntity<Any> {
        quizProcessFacade.terminateActiveQuiz(user.userId!!)
        return ResponseEntity(HttpStatus.OK)
    }
}