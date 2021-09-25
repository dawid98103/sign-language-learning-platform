package pl.dkobylarz.signlearning.domain.quiz.dto

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import pl.dkobylarz.signlearning.util.CustomLocalDateTimeDeserializer
import pl.dkobylarz.signlearning.util.CustomLocalDateTimeSerializer
import java.time.LocalDateTime

data class QuizProcessDTO(
        val userAnswers: MutableMap<QuizQuestionDTO, Boolean>,
        @JsonSerialize(using = CustomLocalDateTimeSerializer::class)
        @JsonDeserialize(using = CustomLocalDateTimeDeserializer::class)
        val startDate: LocalDateTime
)
