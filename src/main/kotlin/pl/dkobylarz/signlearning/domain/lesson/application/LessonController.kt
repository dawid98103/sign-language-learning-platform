package pl.dkobylarz.signlearning.domain.lesson.application

import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.dkobylarz.signlearning.domain.lesson.LessonFacade

@RestController
@RequiredArgsConstructor
@RequestMapping("/lessons")
class LessonController (private val lessonFacade: LessonFacade){
}