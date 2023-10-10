package com.filippo.repos.search.domain

import com.filippo.repos.common.TextResource
import com.filippo.repos.search.R

internal sealed interface ValidationError {
    object WrongFormat : ValidationError
    object EmptyOwner : ValidationError
    object EmptyName : ValidationError
}

internal val ValidationError.errorMessage
    get() = when (this) {
        ValidationError.EmptyName -> TextResource.fromStringRes(R.string.empty_repo)
        ValidationError.EmptyOwner -> TextResource.fromStringRes(R.string.empty_owner)
        ValidationError.WrongFormat -> TextResource.fromStringRes(R.string.wrong_format)
    }
