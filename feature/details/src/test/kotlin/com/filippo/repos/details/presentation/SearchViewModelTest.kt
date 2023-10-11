package com.filippo.repos.details.presentation

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import arrow.core.right
import com.filippo.repos.details.author
import com.filippo.repos.details.domain.GetRepositoryUseCase
import com.filippo.repos.details.message
import com.filippo.repos.details.repositoryId
import com.filippo.repos.details.repositoryName
import com.filippo.repos.details.repositoryOwner
import com.filippo.repos.details.repositoryWithCommits
import com.filippo.repos.details.sha
import com.filippo.repos.navigation.domain.Navigator
import com.filippo.repos.navigation.domain.REPOSITORY_NAME_ARG
import com.filippo.repos.navigation.domain.REPOSITORY_OWNER_ARG
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

class RepositoryDetailsViewModelTest {
    private val testDispatcher = UnconfinedTestDispatcher()
    private val savedStateHandle = SavedStateHandle(emptyMap())

    private val navigator = mockk<Navigator>(relaxUnitFun = true)
    private val getRepoDetails = mockk<GetRepositoryUseCase>()

    private val viewModel
        get() = RepoDetailsViewModel(getRepoDetails, savedStateHandle, navigator, mockk())

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun clear() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should emit repository details`() = runTest {
        // given
        coEvery {
            getRepoDetails(repositoryOwner, repositoryName)
        } returns repositoryWithCommits.right()
        savedStateHandle[REPOSITORY_OWNER_ARG] = repositoryOwner
        savedStateHandle[REPOSITORY_NAME_ARG] = repositoryName

        // when
        viewModel.state.test {
            // then
            awaitItem() shouldBe RepoDetailsState(
                repoName = "$repositoryOwner/$repositoryName",
                content = RepoDetailsState.Content(
                    repoId = repositoryId,
                    commits = listOf(
                        RepoDetailsState.Commit(
                            author = author,
                            sha = sha,
                            message = message
                        )
                    )
                )
            )
        }
    }

    @Test
    fun `should navigate up`() {
        // when
        viewModel.navigateUp()

        // then
        verify { navigator.navigateUp() }
    }
}
