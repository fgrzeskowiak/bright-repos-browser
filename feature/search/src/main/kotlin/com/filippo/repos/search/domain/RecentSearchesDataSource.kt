package com.filippo.repos.search.domain

import com.filippo.repos.search.domain.model.SearchResult

internal interface RecentSearchesDataSource {
    suspend fun recentSearches(): List<SearchResult>
}
