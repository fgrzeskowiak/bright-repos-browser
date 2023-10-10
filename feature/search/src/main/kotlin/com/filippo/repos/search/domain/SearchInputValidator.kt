package com.filippo.repos.search.domain

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import javax.inject.Inject

internal class SearchInputValidator @Inject constructor() {
    fun validate(input: String): Either<ValidationError, Pair<String, String>> = either {
        val segments = input.split(DELIMITER)
        ensure(segments.size == 2) { ValidationError.WrongFormat }

        val (repositoryOwner, repositoryName) = segments
        ensure(repositoryOwner.isNotEmpty()) { ValidationError.EmptyOwner }
        ensure(repositoryName.isNotEmpty()) { ValidationError.EmptyName }

        repositoryOwner to repositoryName
    }
}

private const val DELIMITER = '/'

internal sealed interface ValidationError {
    object WrongFormat : ValidationError
    object EmptyOwner : ValidationError
    object EmptyName : ValidationError
}
