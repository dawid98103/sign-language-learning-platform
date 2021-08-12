package pl.dkobylarz.signlearning.infrastructure.errorhandling

class SignLearningAppError {

    val apiVersion: String
    val error: ErrorBlock

    constructor(
        apiVersion: String,
        status: String,
        message: String,
        domain: String,
        reason: String,
        errorMessage: String
    ) {
        this.apiVersion = apiVersion
        this.error = ErrorBlock(status, message, domain, reason, errorMessage)
    }

    companion object {
        fun fromDefaultAttributeMap(
            apiVersion: String,
            defaultErrorAttributes: Map<String, Any>
        ): SignLearningAppError {
            return SignLearningAppError(
                apiVersion,
                defaultErrorAttributes["status"].toString(),
                defaultErrorAttributes.getOrDefault("message", "no message available") as String,
                defaultErrorAttributes.getOrDefault("path", "no domain available") as String,
                defaultErrorAttributes.getOrDefault("error", "no reason available") as String,
                defaultErrorAttributes["message"] as String
            )
        }
    }

    fun toAttributeMap(): MutableMap<String, Any> {
        return mutableMapOf(
            Pair("apiVersion", apiVersion),
            Pair("error", error)
        )
    }
}
