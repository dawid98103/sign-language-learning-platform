package pl.dkobylarz.signlearning.domain.lessoncompleted.application

import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.dkobylarz.signlearning.domain.lessoncompleted.LessonCompletedFacade
import pl.dkobylarz.signlearning.domain.user.domain.User

@RestController
@RequiredArgsConstructor
@RequestMapping("/lessons")
class LessonStageCompletedController(private val lessonCompletedFacade: LessonCompletedFacade) {

    @PostMapping("/{lessonId}/stage/{stageId}/finish")
    fun setLessonStageAsCompletedByUser(
        @PathVariable lessonId: Int,
        @PathVariable stageId: Int,
        @AuthenticationPrincipal user: User?
    ): ResponseEntity<Any> {
        lessonCompletedFacade.setLessonStageAsCompletedByUser(stageId, user!!)
        return ResponseEntity(HttpStatus.OK)
    }
}