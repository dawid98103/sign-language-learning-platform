package pl.dkobylarz.signlearning.domain.quiz.application

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.dkobylarz.signlearning.domain.quiz.QuizFacade
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizDto

@RestController
@RequiredArgsConstructor
@RequestMapping("/quizzes")
class QuizController(val quizFacade: QuizFacade) {

    @GetMapping("/{lessonId}/quiz")
    fun getQuizForLessonWithCompletionStatus(
        @PathVariable lessonId: Int
    ): ResponseEntity<QuizDto> {
        return ResponseEntity.ok(quizFacade.getQuizForLessonWithCompletionStatus(lessonId))
    }

    @GetMapping("")
    fun getQuizzes(): ResponseEntity<Set<QuizDto>>{
        return ResponseEntity.ok(quizFacade.getQuizzes())
    }
}