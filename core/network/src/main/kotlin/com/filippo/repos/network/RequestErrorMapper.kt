package com.filippo.repos.network

import java.net.SocketTimeoutException
import java.net.UnknownHostException
import retrofit2.HttpException

fun Throwable.toRequestError() = when (this) {
    is UnknownHostException -> RequestError.NoConnection
    is SocketTimeoutException -> RequestError.Timeout
    !is HttpException -> RequestError.Unknown
    else -> RequestError.Unknown
}
