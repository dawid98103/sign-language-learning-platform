package pl.dkobylarz.signlearning.domain.postlike.infrastructure

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import pl.dkobylarz.signlearning.domain.postlike.exception.PostLikeNotFoundException
import pl.dkobylarz.signlearning.infrastructure.errorhandling.SignLearningAppError
import pl.dkobylarz.signlearning.infrastructure.errorhandling.consatnt.AppDomain

@RestControllerAdvice
class PostLikeControllerAdvice(@org.springframework.beans.factory.annotation.Value("\${signlearning.api.version}") val apiVersion: String) {

    @ExceptionHandler(PostLikeNotFoundException::class)
    fun handlePostLikeNotFoundException(ex: PostLikeNotFoundException): ResponseEntity<SignLearningAppError>{
        val error = SignLearningAppError(
            apiVersion,
            HttpStatus.BAD_REQUEST.name,
            "Nazwa użytkownika zajęta!",
            AppDomain.AUTHORIZATION.domainName,
            ex.message!!,
            ex.message
        )

        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }
}