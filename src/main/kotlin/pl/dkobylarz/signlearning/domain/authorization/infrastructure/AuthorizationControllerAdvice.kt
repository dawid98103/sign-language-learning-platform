package pl.dkobylarz.signlearning.domain.authorization.infrastructure

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import pl.dkobylarz.signlearning.domain.authorization.exception.PasswordsNotSameException
import pl.dkobylarz.signlearning.domain.authorization.exception.UsernameAlreadyExistsException
import pl.dkobylarz.signlearning.infrastructure.errorhandling.SignLearningAppError
import pl.dkobylarz.signlearning.infrastructure.errorhandling.consatnt.AppDomain

@RestControllerAdvice
class AuthorizationControllerAdvice(@org.springframework.beans.factory.annotation.Value("\${signlearning.api.version}") val apiVersion: String) {

    @ExceptionHandler(UsernameAlreadyExistsException::class)
    fun handleUsernameAlreadyExistsException(ex: UsernameAlreadyExistsException): ResponseEntity<SignLearningAppError> {
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

    @ExceptionHandler(PasswordsNotSameException::class)
    fun handlePasswordsNotSameException(ex: PasswordsNotSameException): ResponseEntity<SignLearningAppError> {
        val error = SignLearningAppError(
            apiVersion,
            HttpStatus.BAD_REQUEST.name,
            "Podane hasła nie są takie same!",
            AppDomain.AUTHORIZATION.domainName,
            ex.message!!,
            ex.message
        )

        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }
}