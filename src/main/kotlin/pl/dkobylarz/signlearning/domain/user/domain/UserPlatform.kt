package pl.dkobylarz.signlearning.domain.user.domain

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import pl.dkobylarz.signlearning.util.CustomLocalDateTimeDeserializer
import pl.dkobylarz.signlearning.util.CustomLocalDateTimeSerializer
import java.time.LocalDateTime

data class UserPlatform(
    val userId: Int,
    val username: String,
    val password: String,
    val name: String,
    val surname: String,
    val email: String,
    val roleId: Int,
    var points: Int,
    val active: Boolean,
    val avatarUrl: String,
    @JsonSerialize(using = CustomLocalDateTimeSerializer::class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer::class)
    val creationDate: LocalDateTime
)
