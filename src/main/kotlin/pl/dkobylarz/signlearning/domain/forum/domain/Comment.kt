package pl.dkobylarz.signlearning.domain.forum.domain

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import pl.dkobylarz.signlearning.util.CustomLocalDateTimeDeserializer
import pl.dkobylarz.signlearning.util.CustomLocalDateTimeSerializer
import java.time.LocalDateTime

@Table("post_comment")
data class Comment(
        @Id
        val commentId: Int,
        val content: String,
        @JsonSerialize(using = CustomLocalDateTimeSerializer::class)
        @JsonDeserialize(using = CustomLocalDateTimeDeserializer::class)
        val creationDate: LocalDateTime,
        @JsonSerialize(using = CustomLocalDateTimeSerializer::class)
        @JsonDeserialize(using = CustomLocalDateTimeDeserializer::class)
        val updateDate: LocalDateTime,
        val userId: Int
)
