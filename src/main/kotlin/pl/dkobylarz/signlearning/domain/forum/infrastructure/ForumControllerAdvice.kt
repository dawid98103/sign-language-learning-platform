package pl.dkobylarz.signlearning.domain.forum.infrastructure

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import pl.dkobylarz.signlearning.domain.forum.exception.PostNotFoundException
import pl.dkobylarz.signlearning.infrastructure.errorhandling.SignLearningAppError
import pl.dkobylarz.signlearning.infrastructure.errorhandling.consatnt.AppDomain

@RestControllerAdvice
class ForumControllerAdvice(@org.springframework.beans.factory.annotation.Value("\${signlearning.api.version}") val apiVersion: String) {

    @ExceptionHandler(PostNotFoundException::class)
    fun handlePostNotFoundException(ex: PostNotFoundException): ResponseEntity<SignLearningAppError> {
        val error = SignLearningAppError(
            apiVersion,
            HttpStatus.BAD_REQUEST.name,
            "Nie znaleziono posta o podanym ID!",
            AppDomain.FORUM.domainName,
            ex.message!!,
            ex.message
        )

        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }
}