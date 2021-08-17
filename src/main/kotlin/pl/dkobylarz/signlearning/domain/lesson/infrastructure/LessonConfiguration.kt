package pl.dkobylarz.signlearning.domain.lesson.infrastructure

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.dkobylarz.signlearning.domain.lesson.LessonFacade

@Configuration
class LessonConfiguration {

    @Bean
    fun lessonProdDatabase(lessonRepository: LessonRepository): LessonDatabase{
        return LessonDatabaseAdapter(lessonRepository)
    }

    @Bean
    fun lessonFacade(): LessonFacade {
        return LessonFacade()
    }
}