package pl.dkobylarz.signlearning.domain.lesson.application

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.dkobylarz.signlearning.domain.lesson.LessonFacade
import pl.dkobylarz.signlearning.domain.lesson.domain.dto.LessonWithCompletitionStatusDto
import pl.dkobylarz.signlearning.domain.lesson.domain.dto.LessonStageCompletedDto
import pl.dkobylarz.signlearning.domain.lesson.domain.dto.LessonStageElementDto
import pl.dkobylarz.signlearning.domain.user.domain.User

@RestController
@RequiredArgsConstructor
@RequestMapping("/lessons")
class LessonController(private val lessonFacade: LessonFacade) {

    @GetMapping("")
    fun getLessonsWithCompletionStatus(): ResponseEntity<Set<LessonWithCompletitionStatusDto>> {
        return ResponseEntity.ok(lessonFacade.getLessons())
    }

    @GetMapping("/stage/{stageId}/element")
    fun getElementsForStage(@PathVariable stageId: Int): ResponseEntity<Set<LessonStageElementDto>> {
        return ResponseEntity.ok(lessonFacade.getElementsForStage(stageId))
    }

    @GetMapping("/user/completed")
    fun getCompletedLessonsForUser(@AuthenticationPrincipal user: User): ResponseEntity<List<LessonStageCompletedDto>> {
        return ResponseEntity.ok(lessonFacade.getCompletedLessonsForUser(user.userId!!))
    }
}