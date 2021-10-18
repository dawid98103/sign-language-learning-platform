package pl.dkobylarz.signlearning.domain.lesson.domain

import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.lesson.dto.LessonWithCompletionStatusDTO
import pl.dkobylarz.signlearning.domain.lesson.infrastructure.LessonRepository
import java.util.stream.Collectors

@Service
class LessonService(private val lessonRepository: LessonRepository) {

    fun getLessons(): LinkedHashSet<LessonWithCompletionStatusDTO> {
        val lessons: MutableList<Lesson> = lessonRepository.findAll() as MutableList<Lesson>
        return lessons.stream()
            .map { LessonMapper.toDto(it) }
            .sorted { o1, o2 ->
                when {
                    (o1 == null && o2 == null) -> 0
                    (o1.lessonId < o2.lessonId) -> -1
                    else -> 1
                }
            }
            .collect(Collectors.toCollection { LinkedHashSet() })
    }
}