package pl.dkobylarz.signlearning.infrastructure.errorhandling.consatnt

import com.fasterxml.jackson.annotation.JsonValue

enum class AppDomain(val domainId: Int, @JsonValue val domainName: String) {
    AUTHORIZATION(1, "AUTHORIZATION"),
    LESSON(2, "LESSON"),
    LESSON_COMPLETED(3, "LESSON_COMPLETED"),
    QUIZ(4, "QUIZ"),
    QUIZ_COMPLETED(5, "QUIZ_COMPLETED"),
    USER(6, "USER"),
    USER_LOGGING(7, "USER_LOGGING"),
    FORUM(8, "FORUM")
}