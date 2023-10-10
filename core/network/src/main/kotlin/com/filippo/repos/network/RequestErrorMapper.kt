package com.filippo.repos.network

import com.filippo.repos.common.RequestError
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import retrofit2.HttpException

fun Throwable.toRequestError() = when (this) {
    is UnknownHostException -> RequestError.NoConnection
    is SocketTimeoutException -> RequestError.Timeout
    is HttpException -> toRequestError()
    else -> RequestError.Unknown(message)
}

private fun HttpException.toRequestError() = when (code()) {
    400 -> RequestError.BadRequest
    401 -> RequestError.Unauthorized
    404 -> RequestError.NotFound
    500 -> RequestError.ServiceDown
    else -> RequestError.General(code(), message)
}
