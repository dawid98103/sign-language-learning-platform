package pl.dkobylarz.signlearning.domain.quiz.dto

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import pl.dkobylarz.signlearning.util.CustomLocalDateTimeDeserializer
import pl.dkobylarz.signlearning.util.CustomLocalDateTimeSerializer
import java.time.LocalDateTime

data class QuizResultDTO(
    val quizName: String,
    val pointsToGain: Int,
    val pointsGained: Int,
    val quizAnswersSummary: Set<QuizResultEntryDTO>,
    @JsonSerialize(using = CustomLocalDateTimeSerializer::class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer::class)
    val startDate: LocalDateTime,
    @JsonSerialize(using = CustomLocalDateTimeSerializer::class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer::class)
    val finishDate: LocalDateTime
)
