package pl.dkobylarz.signlearning.domain.lesson.application

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.dkobylarz.signlearning.domain.lesson.LessonFacade
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageCompletionDto
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageElementDto
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonWithCompletionStatusDto
import pl.dkobylarz.signlearning.domain.user.domain.User

@RestController
@RequiredArgsConstructor
@RequestMapping("/lessons")
class LessonController(private val lessonFacade: LessonFacade) {

    @GetMapping("")
    fun getLessonsWithCompletionStatus(): ResponseEntity<Set<LessonWithCompletionStatusDto>> {
        return ResponseEntity.ok(lessonFacade.getLessons())
    }

    @GetMapping("/{lessonId}/stage")
    fun getStagesForLessonWithCompletionStatus(
        @PathVariable lessonId: Int,
        @AuthenticationPrincipal user: User?
    ): ResponseEntity<Set<LessonStageCompletionDto>> {
        return ResponseEntity.ok(lessonFacade.getStagesForLessonWithCompletionStatus(lessonId, user))
    }

    @GetMapping("/{lessonId}/stage/{stageId}/element")
    fun getElementsForLessonStage(
        @PathVariable lessonId: Int,
        @PathVariable stageId: Int
    ): ResponseEntity<Set<LessonStageElementDto>> {
        return ResponseEntity.ok(lessonFacade.getElementsForLessonStage(stageId))
    }
}