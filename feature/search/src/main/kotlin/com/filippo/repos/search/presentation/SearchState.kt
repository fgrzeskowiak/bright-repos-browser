package com.filippo.repos.search.presentation

data class SearchState(
    val recentSearches: List<String> = emptyList(),
    val error: String? = null,
)
