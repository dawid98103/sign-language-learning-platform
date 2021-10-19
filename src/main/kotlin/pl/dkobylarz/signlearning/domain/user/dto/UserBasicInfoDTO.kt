package pl.dkobylarz.signlearning.domain.user.dto

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import pl.dkobylarz.signlearning.util.CustomLocalDateTimeDeserializer
import pl.dkobylarz.signlearning.util.CustomLocalDateTimeSerializer
import java.time.LocalDateTime

data class UserBasicInfoDTO(
    @JsonSerialize(using = CustomLocalDateTimeSerializer::class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer::class)
    val lastActivityDateTime: LocalDateTime,
    val consecutiveDays: Int,
    val gainedPoints: Int
)
