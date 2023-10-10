package com.filippo.repos.search.presentation

import com.filippo.repos.common.TextResource

internal data class SearchState(
    val recentSearches: List<String> = emptyList(),
    val error: TextResource? = null,
)
