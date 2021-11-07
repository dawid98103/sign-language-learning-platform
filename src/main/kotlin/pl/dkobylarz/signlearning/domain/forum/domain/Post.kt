package pl.dkobylarz.signlearning.domain.forum.domain

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table
import pl.dkobylarz.signlearning.util.CustomLocalDateTimeDeserializer
import pl.dkobylarz.signlearning.util.CustomLocalDateTimeSerializer
import java.time.LocalDateTime

@Table("post")
data class Post(
    @Id
    val postId: Int,
    val topic: String,
    val content: String,
    @JsonSerialize(using = CustomLocalDateTimeSerializer::class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer::class)
    val creationDate: LocalDateTime,
    @JsonSerialize(using = CustomLocalDateTimeSerializer::class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer::class)
    val updateDate: LocalDateTime,
    val userId: Int,
    @MappedCollection(idColumn = "post_id", keyColumn = "post_id")
    var comments: MutableSet<Comment>
) {
    fun addComment(comment: Comment) {
        this.comments.add(comment)
    }

    fun deleteComment(commentId: Int) {
        this.comments = this.comments.asSequence()
            .filter { it.commentId != commentId }
            .toMutableSet()
    }
}
