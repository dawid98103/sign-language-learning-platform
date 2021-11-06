package pl.dkobylarz.signlearning.domain.forum.dto

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import pl.dkobylarz.signlearning.util.CustomLocalDateTimeDeserializer
import pl.dkobylarz.signlearning.util.CustomLocalDateTimeSerializer
import java.time.LocalDateTime

data class CommentDTO(
    val commentId: Int,
    val content: String,
    @JsonSerialize(using = CustomLocalDateTimeSerializer::class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer::class)
    val creationDate: LocalDateTime,
    val avatarUrl: String,
    val author: String,
    val editable: Boolean
)
