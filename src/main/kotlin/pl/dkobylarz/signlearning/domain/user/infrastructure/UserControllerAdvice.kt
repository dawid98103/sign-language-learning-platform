package pl.dkobylarz.signlearning.domain.user.infrastructure

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import pl.dkobylarz.signlearning.domain.user.domain.exception.UserNotFoundException
import pl.dkobylarz.signlearning.infrastructure.errorhandling.SignLearningAppError

@RestControllerAdvice
class UserControllerAdvice {

    @Value("\${signlearning.api.version}")
    lateinit var apiVersion: String

    @ExceptionHandler(UserNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleUserNotExistsException(exception: UserNotFoundException): ResponseEntity<SignLearningAppError> {
        val error = SignLearningAppError(
            apiVersion,
            HttpStatus.NOT_FOUND.value().toString(),
            "Given user not exists",
            "User",
            "User with given id not exists",
            "User not exists in database"
        )
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }
}