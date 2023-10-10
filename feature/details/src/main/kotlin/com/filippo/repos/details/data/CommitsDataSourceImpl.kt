package com.filippo.repos.details.data

import arrow.core.raise.either
import com.filippo.repos.database.dao.CommitsDao
import com.filippo.repos.details.data.remote.GithubApi
import com.filippo.repos.details.domain.CommitsDataSource
import com.filippo.repos.details.domain.model.Commit
import com.filippo.repos.network.ApiResponse
import com.filippo.repos.network.apiCall
import javax.inject.Inject

internal class CommitsDataSourceImpl @Inject constructor(
    private val api: GithubApi,
    private val dao: CommitsDao,
) : CommitsDataSource {

    override suspend fun getCommits(
        repositoryOwner: String,
        repositoryName: String,
    ): ApiResponse<List<Commit>> = either {
        val jointRepositoryName = "$repositoryOwner/$repositoryName"
        val localCommits = dao.getCommits(jointRepositoryName).toDomain()

        localCommits.takeIf { it.isNotEmpty() }
            ?: getCommitsFromRemote(repositoryOwner, repositoryName)
                .onRight { dao.insertAll(it.toEntity(jointRepositoryName)) }
                .bind()
    }

    private suspend fun getCommitsFromRemote(
        repositoryOwner: String,
        repositoryName: String,
    ): ApiResponse<List<Commit>> =
        apiCall { api.getCommits(repositoryOwner, repositoryName).toDomain() }
}
