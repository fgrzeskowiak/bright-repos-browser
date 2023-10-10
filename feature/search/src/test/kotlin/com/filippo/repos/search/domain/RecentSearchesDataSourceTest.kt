package com.filippo.repos.search.domain

import com.filippo.repos.database.dao.RepositoriesDao
import com.filippo.repos.database.model.RepositoryEntity
import com.filippo.repos.search.data.RecentSearchesDataSourceImpl
import com.filippo.repos.search.domain.model.SearchResult
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RecentSearchesDataSourceTest {
    private val id = "id"
    private val owner = "owner"
    private val name = "name"

    private val dao = mockk<RepositoriesDao>()

    private val dataSource
        get() = RecentSearchesDataSourceImpl(dao)

    @Test
    fun `should emit search results from database`() = runTest {
        // given
        coEvery { dao.getAllRepositories() } returns listOf(RepositoryEntity(id, name, owner))

        // when
        val result = dataSource.recentSearches()

        // then
        result shouldBe listOf(SearchResult(owner, name))
    }
}
