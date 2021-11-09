package pl.dkobylarz.signlearning.domain.user.infrastructure

import org.springframework.data.repository.CrudRepository
import pl.dkobylarz.signlearning.domain.user.domain.Friend

interface FriendRepository : CrudRepository<Friend, Int> {
    fun findByFirstUserId(userId: Int): Set<Friend>
    fun findByFirstUserIdAndSecondUserId(firstUserId: Int, secondUserId: Int): Friend
}