package pl.dkobylarz.signlearning.infrastructure.errorhandling

import com.aventrix.jnanoid.jnanoid.NanoIdUtils

class ErrorBlock {

    var nanoId: String = ""
    var code: String = ""
    var localizedMessage: String = ""
    var errors: List<Error> = emptyList()


    constructor(code: String, localizedMessage: String, domain: String, reason: String, errorMessage: String) {
        this.code = code
        this.localizedMessage = localizedMessage
        this.nanoId = NanoIdUtils.randomNanoId()
        this.errors = listOf(Error(domain, reason, errorMessage))
    }

    constructor(nanoId: String, code: String, message: String, errors: List<Error>) {
        this.code = code
        this.errors = errors
        this.localizedMessage = message
        this.nanoId = nanoId
    }

    companion object {
        fun create(s: ErrorBlock, message: String): ErrorBlock {
            return ErrorBlock(s.nanoId, s.code, message, s.errors)
        }
    }
}