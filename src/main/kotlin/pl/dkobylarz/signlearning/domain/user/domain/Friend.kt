package pl.dkobylarz.signlearning.domain.user.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("friend")
data class Friend(
    @Id
    val friendId: Int,
    val firstUserId: Int,
    val secondUserId: Int
)
