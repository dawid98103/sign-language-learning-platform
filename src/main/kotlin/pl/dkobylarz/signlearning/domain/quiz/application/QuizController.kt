package pl.dkobylarz.signlearning.domain.quiz.application

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.dkobylarz.signlearning.domain.quiz.QuizFacade
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizCompletionStatusDTO
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizQuestionDTO
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizResultDTO
import pl.dkobylarz.signlearning.domain.quizprocess.QuizProcessFacade
import pl.dkobylarz.signlearning.domain.user.domain.User

@RestController
@RequiredArgsConstructor
@RequestMapping("/quizzes")
class QuizController(
    private val quizFacade: QuizFacade
) {

    @GetMapping("")
    fun getQuizzesWithCompletionStatus(@AuthenticationPrincipal user: User?): ResponseEntity<Any> {
        return if (user != null) {
            ResponseEntity.ok(quizFacade.getQuizzesWithCompletionStatus(user.userId!!))
        } else {
            ResponseEntity.ok(null)
        }
    }

    @GetMapping("/{lessonId}/quiz/{quizId}/result")
    fun getQuizResult(
        @PathVariable lessonId: Int,
        @PathVariable quizId: Int,
        @AuthenticationPrincipal user: User
    ): ResponseEntity<QuizResultDTO> {
        return ResponseEntity.ok(quizFacade.getQuizResult(quizId, lessonId, user.userId!!))
    }

    @GetMapping("/{lessonId}/quiz")
    fun getQuizForLessonWithCompletionStatus(
        @PathVariable lessonId: Int,
        @AuthenticationPrincipal user: User
    ): ResponseEntity<QuizCompletionStatusDTO> {
        return ResponseEntity.ok(quizFacade.getQuizForLessonWithCompletionStatus(lessonId, user))
    }

    @GetMapping("/{lessonId}/quiz/{quizId}/questions")
    fun getQuestionsForGivenQuizWithAnswers(
        @PathVariable lessonId: Int,
        @PathVariable quizId: Int
    ): ResponseEntity<Set<QuizQuestionDTO>> {
        return ResponseEntity.ok(quizFacade.getQuestionsForGivenQuizWithAnswers(quizId))
    }
}