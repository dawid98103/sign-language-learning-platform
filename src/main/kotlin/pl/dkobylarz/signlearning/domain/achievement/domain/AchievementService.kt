package pl.dkobylarz.signlearning.domain.achievement.domain

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import pl.dkobylarz.signlearning.domain.achievement.dto.AchievementDTO
import pl.dkobylarz.signlearning.domain.achievement.infrastructure.*
import pl.dkobylarz.signlearning.domain.achievementuser.dto.AchievementUserDTO
import pl.dkobylarz.signlearning.domain.forum.dto.PostDTO
import pl.dkobylarz.signlearning.domain.quiz.dto.QuizDTO
import pl.dkobylarz.signlearning.domain.quizcompleted.dto.QuizCompletedShortDTO
import pl.dkobylarz.signlearning.domain.user.domain.Friend
import pl.dkobylarz.signlearning.domain.user.domain.User
import java.time.LocalDateTime

@Service
@RequiredArgsConstructor
class AchievementService(
    private val achievementRepository: AchievementRepository,
    private val achievementUserClient: AchievementUserClient,
    private val forumClient: ForumClient,
    private val quizCompletedClient: QuizCompletedClient,
    private val quizClient: QuizClient,
    private val userClient: UserClient
) {

    fun getAchievementsForUser(user: User): Set<AchievementDTO> {
        val achievements = achievementRepository.findAll()
        val assignedAchievements = achievementUserClient.getAllByUserId(user.userId!!)

        return achievements.asSequence()
            .map { achievement ->
                if (assignedAchievements.any { it.achievementId == achievement.achievementId }) {
                    val completedAchievement =
                        assignedAchievements.find { it.achievementId == achievement.achievementId }
                    AchievementMapper.toDto(achievement, completedAchievement!!.creationDateTime)
                } else {
                    AchievementMapper.toDto(achievement)
                }
            }.toSet()
    }

    fun calculateUnassignedAchievements(user: User) {
        val achievements = achievementRepository.findAll()
        val userPosts = forumClient.getUserPosts(user)
        val userLoginsInRow = userClient.getConsecutiveLearningDays(user.userId!!)
        val assignedAchievements = achievementUserClient.getAllByUserId(user.userId!!)
        val unassignedAchievements = getUnassignedAchievements(achievements, assignedAchievements)

        unassignedAchievements.forEach {
            when (it.name) {
                AchievementType.FINISH_ALL_QUIZZES.name -> {
                    val allQuizzes = quizClient.getQuizzes()
                    val quizzesCompletedByUser = quizCompletedClient.getCompletedQuizzesByUser(user.userId!!)
                    checkFinishAllQuizzes(allQuizzes, quizzesCompletedByUser, user.userId!!, it)
                }
                AchievementType.FIRST_ADDED_POST.name -> {
                    checkFirstAddedPost(userPosts, user.userId!!, it)
                }
                AchievementType.FIRST_OBSERVER.name -> {
                    val userFriends = userClient.getUserFriends(user.userId!!)
                    checkFirstObserver(userFriends, user.userId!!, it)
                }
                AchievementType.FIRST_FINISHED_QUIZ.name -> {
                    val quizzesCompletedByUser = quizCompletedClient.getCompletedQuizzesByUser(user.userId!!)
                    checkFirstFinishedQuiz(quizzesCompletedByUser, user.userId!!, it)
                }
                AchievementType.FIVE_LOGINS_IN_A_ROW.name -> {
                    checkFiveLoginsInARow(userLoginsInRow, user.userId!!, it)
                }
                AchievementType.TEN_LOGINS_IN_A_ROW.name -> {
                    checkTenLoginsInRow(userLoginsInRow, user.userId!!, it)
                }
                AchievementType.ONE_YEAR_ON_PLATFORM.name -> {
                    checkOneYearOnPlatform(user, it)
                }
                AchievementType.TEN_ADDED_POST.name -> {
                    checkTenAddedPost(userPosts, user.userId!!, it)
                }
            }
        }
    }

    private fun checkFinishAllQuizzes(
        allQuizzes: Set<QuizDTO>,
        quizzesCompletedByUser: Set<QuizCompletedShortDTO>,
        userId: Int,
        achievement: Achievement
    ) {
        val quizzesQuantity = allQuizzes.size
        val quizzesCompletedByUserQuantity = quizzesCompletedByUser.size
        if (quizzesQuantity == quizzesCompletedByUserQuantity) {
            achievementUserClient.save(achievement, userId)
            userClient.addPoints(userId, achievement.pointsToGain)
        }
    }

    private fun checkFirstFinishedQuiz(
        quizzesCompletedByUser: Set<QuizCompletedShortDTO>,
        userId: Int,
        achievement: Achievement
    ) {
        if (quizzesCompletedByUser.isNotEmpty()) {
            achievementUserClient.save(achievement, userId)
            userClient.addPoints(userId, achievement.pointsToGain)
        }
    }

    private fun checkFirstObserver(userFriends: Set<Friend>, userId: Int, achievement: Achievement) {
        if (userFriends.isNotEmpty()) {
            achievementUserClient.save(achievement, userId)
            userClient.addPoints(userId, achievement.pointsToGain)
        }
    }

    private fun checkFiveLoginsInARow(loginsInRow: Int, userId: Int, achievement: Achievement) {
        if (loginsInRow >= 5) {
            achievementUserClient.save(achievement, userId)
            userClient.addPoints(userId, achievement.pointsToGain)
        }
    }

    private fun checkTenLoginsInRow(loginsInRow: Int, userId: Int, achievement: Achievement) {
        if (loginsInRow >= 10) {
            achievementUserClient.save(achievement, userId)
            userClient.addPoints(userId, achievement.pointsToGain)
        }
    }

    private fun checkOneYearOnPlatform(user: User, achievement: Achievement) {
        val registerDate = user.creationDate!!
        val currentDate = LocalDateTime.now()
        if (registerDate.plusYears(1).isBefore(currentDate)) {
            achievementUserClient.save(achievement, user.userId!!)
            userClient.addPoints(user.userId!!, achievement.pointsToGain)
        }
    }

    private fun checkFirstAddedPost(userPosts: Set<PostDTO>, userId: Int, achievement: Achievement) {
        if (userPosts.isNotEmpty()) {
            achievementUserClient.save(achievement, userId)
            userClient.addPoints(userId, achievement.pointsToGain)
        }
    }

    private fun checkTenAddedPost(userPosts: Set<PostDTO>, userId: Int, achievement: Achievement) {
        if (userPosts.size > 10) {
            achievementUserClient.save(achievement, userId)
            userClient.addPoints(userId, achievement.pointsToGain)
        }
    }

    private fun getUnassignedAchievements(
        achievements: Set<Achievement>,
        assignedAchievements: Set<AchievementUserDTO>
    ): Set<Achievement> {
        return achievements.asSequence()
            .filter { achievement -> assignedAchievements.find { it.achievementId == achievement.achievementId } == null }
            .toSet()
    }
}