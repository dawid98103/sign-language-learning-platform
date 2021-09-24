package pl.dkobylarz.signlearning.domain.lesson.application

import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import pl.dkobylarz.signlearning.domain.lesson.LessonFacade
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageCompletionDTO
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonStageElementDTO
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonWithCompletionStatusDTO
import pl.dkobylarz.signlearning.domain.user.domain.User

@RestController
@RequiredArgsConstructor
@RequestMapping("/lessons")
class LessonController(private val lessonFacade: LessonFacade) {

    @GetMapping("")
    fun getLessonsWithCompletionStatus(): ResponseEntity<Set<LessonWithCompletionStatusDTO>> {
        return ResponseEntity.ok(lessonFacade.getLessons())
    }

    @GetMapping("/{lessonId}/stage")
    fun getStagesForLessonWithCompletionStatus(
        @PathVariable lessonId: Int,
        @AuthenticationPrincipal user: User?
    ): ResponseEntity<Set<LessonStageCompletionDTO>> {
        return ResponseEntity.ok(lessonFacade.getStagesForLessonWithCompletionStatus(lessonId, user))
    }

    @GetMapping("/{lessonId}/stage/{stageId}/element")
    fun getElementsForLessonStage(
        @PathVariable lessonId: Int,
        @PathVariable stageId: Int
    ): ResponseEntity<Set<LessonStageElementDTO>> {
        return ResponseEntity.ok(lessonFacade.getElementsForLessonStage(stageId))
    }

    @PostMapping("/{lessonId}/stage/{stageId}/finish")
    fun setLessonStageAsCompletedByUser(
        @PathVariable lessonId: Int,
        @PathVariable stageId: Int,
        @AuthenticationPrincipal user: User?
    ): ResponseEntity<Any> {
        lessonFacade.setLessonStageAsCompletedByUser(stageId, user)
        return ResponseEntity(HttpStatus.OK)
    }
}