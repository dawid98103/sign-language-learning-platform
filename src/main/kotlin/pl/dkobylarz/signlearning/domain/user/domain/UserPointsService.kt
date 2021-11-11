package pl.dkobylarz.signlearning.domain.user.domain

import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.user.infrastructure.UserRepository
import java.util.logging.Logger

@Service
class UserPointsService(private val userRepository: UserRepository) {

    companion object {
        val LOG = Logger.getLogger(UserPointsService::class.java.name)
    }

    fun addPointsToAccount(userId: Int, pointsToGain: Int) {
        LOG.info("[USER POINTS]: Added $pointsToGain to user with ID: $userId")

        val user = userRepository.findUserByUserId(userId)
        user.points = (user.points!! + pointsToGain)
        userRepository.save(user)
    }
}