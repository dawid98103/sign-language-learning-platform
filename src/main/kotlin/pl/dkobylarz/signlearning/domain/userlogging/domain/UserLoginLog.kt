package pl.dkobylarz.signlearning.domain.userlogging.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("user_login_log")
data class UserLoginLog(
    @Id
    val logId: Int,
    val userId: Int,
    val loginTimestamp: LocalDateTime
)
