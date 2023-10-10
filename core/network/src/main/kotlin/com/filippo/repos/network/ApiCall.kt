package com.filippo.repos.network

import arrow.core.Either

typealias ApiResponse<Response> = Either<RequestError, Response>

suspend inline fun <Response> apiCall(
    crossinline request: suspend () -> Response,
): Either<RequestError, Response> =
    Either.catch { request() }
        .mapLeft { it.toRequestError() }

