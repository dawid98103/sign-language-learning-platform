package pl.dkobylarz.signlearning.domain.user.constant

enum class PointsToGain(val points: Int) {
    COMPLETE_STAGE(5),
    FIVE_DAYS_IN_ROW(5),
    COMPLETE_QUIZ(10),
}