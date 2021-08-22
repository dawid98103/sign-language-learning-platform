package pl.dkobylarz.signlearning.domain.lesson.application

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.dkobylarz.signlearning.domain.lesson.LessonFacade
import pl.dkobylarz.signlearning.domain.lesson.domain.dto.LessonDto
import pl.dkobylarz.signlearning.domain.lesson.domain.dto.LessonStageElementDto

@RestController
@RequiredArgsConstructor
@RequestMapping("/lessons")
class LessonController(private val lessonFacade: LessonFacade) {

    @GetMapping("")
    fun getLessons(): ResponseEntity<Set<LessonDto>> {
        return ResponseEntity.ok(lessonFacade.getLessons())
    }

    @GetMapping("/stage/{stageId}/element")
    fun getElementsForStage(@PathVariable stageId: Int): ResponseEntity<Set<LessonStageElementDto>> {
        return ResponseEntity.ok(lessonFacade.getElementsForStage(stageId))
    }
}