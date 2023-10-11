package com.filippo.repos.search.presentation

import app.cash.turbine.test
import arrow.core.right
import com.filippo.repos.navigation.domain.Navigator
import com.filippo.repos.search.domain.RecentSearchesDataSource
import com.filippo.repos.search.domain.SearchInputValidator
import com.filippo.repos.search.domain.model.SearchResult
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class SearchViewModelTest {
    private val testDispatcher = UnconfinedTestDispatcher()
    private val repositoryOwner = "owner"
    private val repositoryName = "name"

    private val navigator = mockk<Navigator>(relaxUnitFun = true)
    private val validator = mockk<SearchInputValidator>()
    private val dataSource = mockk<RecentSearchesDataSource>()

    private val viewModel
        get() = SearchViewModel(navigator, validator, dataSource)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun clear() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should emit recent searches`() = runTest {
        // given

        coEvery { dataSource.recentSearches() } returns listOf(
            SearchResult(repositoryOwner, repositoryName)
        )

        // when
        viewModel.state.test {
            // then
            val expectedState =
                SearchState(recentSearches = listOf("$repositoryOwner/$repositoryName"))
            awaitItem() shouldBe expectedState
        }
    }

    @Test
    fun `should navigate to details when input is validated`() {
        // given
        val input = "$repositoryOwner/$repositoryName"
        coEvery { validator.validate(input) } returns Pair(repositoryOwner, repositoryName).right()

        // when
        viewModel.search(input)

        // then
        verify { navigator.openDetails(repositoryOwner, repositoryName) }
    }
}
