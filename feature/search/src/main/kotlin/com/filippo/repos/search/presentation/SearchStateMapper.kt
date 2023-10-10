package com.filippo.repos.search.presentation

import com.filippo.repos.search.domain.ValidationError
import com.filippo.repos.search.domain.errorMessage
import com.filippo.repos.search.domain.model.SearchResult

internal fun createSearchState(
    validationError: ValidationError?,
    recentSearches: List<SearchResult>,
) =
    SearchState(
        recentSearches = recentSearches.map { "${it.repositoryOwner}/${it.repositoryName}" },
        error = validationError?.errorMessage
    )
