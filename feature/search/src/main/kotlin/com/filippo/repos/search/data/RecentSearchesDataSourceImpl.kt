package com.filippo.repos.search.data

import com.filippo.repos.database.dao.RepositoriesDao
import com.filippo.repos.search.domain.RecentSearchesDataSource
import com.filippo.repos.search.domain.model.SearchResult
import javax.inject.Inject

internal class RecentSearchesDataSourceImpl @Inject constructor(
    private val repositoriesDao: RepositoriesDao,
) : RecentSearchesDataSource {

    override suspend fun recentSearches(): List<SearchResult> = repositoriesDao.getAllRepositories()
        .map { it.toDomain() }
}
