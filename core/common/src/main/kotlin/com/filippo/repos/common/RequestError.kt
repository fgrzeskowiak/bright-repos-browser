package com.filippo.repos.common

sealed interface RequestError {
    object NoConnection : RequestError
    object Timeout : RequestError
    object BadRequest : RequestError
    object NotFound : RequestError
    object ServiceDown : RequestError
    object Unauthorized : RequestError
    data class General(val code: Int, val message: String?) : RequestError
    data class Unknown(val message: String?) : RequestError
}
