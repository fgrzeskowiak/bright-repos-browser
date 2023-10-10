package com.filippo.repos.common

val RequestError.errorMessage: TextResource
    get() = when (this) {
        RequestError.NoConnection -> TextResource.fromStringRes(R.string.connection_error)
        RequestError.Timeout -> TextResource.fromStringRes(R.string.timeout_error)
        is RequestError.General -> TextResource.fromStringResFormatted(
            R.string.general_request_error,
            code,
            message.orEmpty()
        )

        is RequestError.Unknown -> TextResource.fromStringResFormatted(
            R.string.unknown_error,
            message.orEmpty()
        )

        is RequestError.Unauthorized -> TextResource.fromStringRes(R.string.unauthorized_error)
        RequestError.BadRequest -> TextResource.fromStringRes(R.string.bad_request_error)
        RequestError.NotFound -> TextResource.fromStringRes(R.string.not_found_error)
        RequestError.ServiceDown -> TextResource.fromStringRes(R.string.server_error)
    }
