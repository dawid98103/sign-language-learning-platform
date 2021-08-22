package pl.dkobylarz.signlearning.domain.lesson.infrastructure

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.dkobylarz.signlearning.domain.lesson.LessonFacade
import pl.dkobylarz.signlearning.domain.lesson.domain.LessonService

@Configuration
class LessonConfiguration {

    @Bean
    @ConditionalOnProperty(name = ["signlearning.db.mock"], havingValue = "true")
    fun lessonTestDatabase(): LessonDatabase{
        return LessonDatabaseInMemoryAdapter()
    }

    @Bean
    @ConditionalOnProperty(name = ["signlearning.db.mock"], havingValue = "false")
    fun lessonProdDatabase(lessonRepository: LessonRepository): LessonDatabase {
        return LessonDatabaseAdapter(lessonRepository)
    }

    @Bean
    @ConditionalOnProperty(name = ["signlearning.db.mock"], havingValue = "false")
    fun lessonStageProdDatabase(lessonStageRepository: LessonStageRepository): LessonStageDatabase {
        return LessonStageDatabaseAdapter(lessonStageRepository)
    }

    @Bean
    fun lessonFacade(lessonDatabase: LessonDatabase, lessonStageDatabase: LessonStageDatabase): LessonFacade {
        val lessonService = LessonService(lessonDatabase, lessonStageDatabase)
        return LessonFacade(lessonService)
    }
}