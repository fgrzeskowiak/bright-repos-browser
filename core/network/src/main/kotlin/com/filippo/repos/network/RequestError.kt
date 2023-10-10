package com.filippo.repos.network

sealed interface RequestError {
    object NoConnection : RequestError
    object Timeout : RequestError
    object Unknown : RequestError
}
