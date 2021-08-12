package pl.dkobylarz.signlearning.infrastructure.errorhandling

import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes
import org.springframework.web.context.request.WebRequest

class SignLearningErrorAttributes(val apiVersion: String) : DefaultErrorAttributes() {

    override fun getErrorAttributes(webRequest: WebRequest?, options: ErrorAttributeOptions?): MutableMap<String, Any> {
        val defaultErrorAttributes: Map<String, Any> = super.getErrorAttributes(webRequest, options)
        val signLearningAppError: SignLearningAppError = SignLearningAppError.fromDefaultAttributeMap(
            apiVersion,
            defaultErrorAttributes
        )
        return signLearningAppError.toAttributeMap()
    }
}
