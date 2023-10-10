package com.filippo.repos.details.data

import com.filippo.repos.common.RequestError
import com.filippo.repos.database.dao.CommitsDao
import com.filippo.repos.details.commits
import com.filippo.repos.details.commitsEntity
import com.filippo.repos.details.commitsResponse
import com.filippo.repos.details.data.remote.GithubApi
import com.filippo.repos.details.repositoryName
import com.filippo.repos.details.repositoryOwner
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.mockk.called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CommitsDataSourceTest {
    private val api = mockk<GithubApi>()
    private val dao = mockk<CommitsDao>(relaxUnitFun = true) {
        coEvery { getCommits(repositoryName) } returns emptyList()
    }
    private val dataSource
        get() = CommitsDataSourceImpl(api, dao)

    @Test
    fun `should get commits from remote when database is empty`() = runTest {
        // given
        coEvery { dao.getCommits(repositoryName) } returns emptyList()
        coEvery { api.getCommits(repositoryOwner, repositoryName) } returns commitsResponse

        // when
        val result = dataSource.getCommits(repositoryOwner, repositoryName)

        // then
        coVerify(exactly = 1) { api.getCommits(repositoryOwner, repositoryName) }
        result shouldBeRight commits
    }

    @Test
    fun `should get repository from database and not call api when database is not empty`() =
        runTest {
            // given
            coEvery { dao.getCommits(repositoryName) } returns commitsEntity

            // when
            val result = dataSource.getCommits(repositoryOwner, repositoryName)

            // then
            coVerify { api wasNot called }
            result shouldBeRight commits
        }

    @Test
    fun `should return request error when getRepository() fails`() = runTest {
        // given
        coEvery { api.getCommits(repositoryOwner, repositoryName) } throws RuntimeException()

        // when
        val result = dataSource.getCommits(repositoryOwner, repositoryName)

        // then
        result shouldBeLeft RequestError.NotFound
    }

    @Test
    fun `should insert repository into database when request is successful`() = runTest {
        // given
        coEvery { api.getCommits(repositoryOwner, repositoryName) } returns commitsResponse

        // when
        dataSource.getCommits(repositoryOwner, repositoryName)

        // then
        coVerify { dao.insertAll(commitsEntity) }
    }
}
