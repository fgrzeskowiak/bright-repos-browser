package com.filippo.repos.details.domain

import arrow.core.left
import arrow.core.right
import com.filippo.repos.details.commits
import com.filippo.repos.details.repository
import com.filippo.repos.details.repositoryWithCommits
import com.filippo.repos.network.RequestError
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetRepositoryUseCaseTest {
    private val repositoryOwner = "owner"
    private val repositoryName = "name"

    private val commitsDataSource = mockk<CommitsDataSource>()
    private val repositoryDataSource = mockk<RepositoryDataSource>()

    private val useCase
        get() = GetRepositoryUseCase(commitsDataSource, repositoryDataSource)

    @Test
    fun `should return error when repository request fails`() = runTest {
        // given
        coEvery {
            repositoryDataSource.getRepository(repositoryOwner, repositoryName)
        } returns RequestError.Unknown.left()

        coEvery {
            commitsDataSource.getCommits(repositoryOwner, repositoryName)
        } returns commits.right()

        // when
        val result = useCase(repositoryOwner, repositoryName)

        // then
        result shouldBeLeft RequestError.Unknown
    }

    @Test
    fun `should return error when commits request fails`() = runTest {
        // given
        coEvery {
            repositoryDataSource.getRepository(repositoryOwner, repositoryName)
        } returns repository.right()

        coEvery {
            commitsDataSource.getCommits(repositoryOwner, repositoryName)
        } returns RequestError.Unknown.left()

        // when
        val result = useCase(repositoryOwner, repositoryName)

        // then
        result shouldBeLeft RequestError.Unknown
    }

    @Test
    fun `should return repository with commits when both requests are successful`() = runTest {
        // given
        coEvery {
            repositoryDataSource.getRepository(repositoryOwner, repositoryName)
        } returns repository.right()

        coEvery {
            commitsDataSource.getCommits(repositoryOwner, repositoryName)
        } returns commits.right()

        // when
        val result = useCase(repositoryOwner, repositoryName)

        // then
        result shouldBeRight repositoryWithCommits
    }

}
