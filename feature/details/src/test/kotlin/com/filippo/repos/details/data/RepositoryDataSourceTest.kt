package com.filippo.repos.details.data

import com.filippo.repos.common.RequestError
import com.filippo.repos.database.dao.RepositoriesDao
import com.filippo.repos.details.data.remote.GithubApi
import com.filippo.repos.details.repository
import com.filippo.repos.details.repositoryEntity
import com.filippo.repos.details.repositoryName
import com.filippo.repos.details.repositoryOwner
import com.filippo.repos.details.repositoryResponse
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.mockk.called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RepositoryDataSourceTest {
    private val api = mockk<GithubApi>()
    private val dao = mockk<RepositoriesDao>(relaxUnitFun = true) {
        coEvery { getRepository(repositoryOwner, repositoryName) } returns null
    }
    private val dataSource
        get() = RepositoryDataSourceImpl(api, dao)

    @Test
    fun `should get repository from remote when database is empty`() = runTest {
        // given
        coEvery { dao.getRepository(repositoryOwner, repositoryName) } returns null
        coEvery { api.getRepository(repositoryOwner, repositoryName) } returns repositoryResponse

        // when
        val result = dataSource.getRepository(repositoryOwner, repositoryName)

        // then
        coVerify(exactly = 1) { api.getRepository(repositoryOwner, repositoryName) }
        result shouldBeRight repository
    }

    @Test
    fun `should get repository from database and not call api when database is not empty`() =
        runTest {
            // given
            coEvery { dao.getRepository(repositoryOwner, repositoryName) } returns repositoryEntity

            // when
            val result = dataSource.getRepository(repositoryOwner, repositoryName)

            // then
            coVerify { api wasNot called }
            result shouldBeRight repository
        }

    @Test
    fun `should return request error when getRepository() fails`() = runTest {
        // given
        coEvery { api.getRepository(repositoryOwner, repositoryName) } throws RuntimeException()

        // when
        val result = dataSource.getRepository(repositoryOwner, repositoryName)

        // then
        result shouldBeLeft RequestError.NotFound
    }

    @Test
    fun `should insert repository into database when request is successful`() = runTest {
        // given
        coEvery { api.getRepository(repositoryOwner, repositoryName) } returns repositoryResponse

        // when
        dataSource.getRepository(repositoryOwner, repositoryName)

        // then
        coVerify { dao.insert(repositoryEntity) }
    }
}
