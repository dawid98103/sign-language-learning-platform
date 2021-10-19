package pl.dkobylarz.signlearning.domain.userlogging.infrastructure

import org.springframework.data.repository.CrudRepository
import pl.dkobylarz.signlearning.domain.userlogging.domain.UserLoginLog

interface UserLoginLogRepository : CrudRepository<UserLoginLog, Int> {

    fun findByUserId(userId: Int): Set<UserLoginLog>
}