package com.filippo.repos.details.data

import com.filippo.repos.details.data.remote.GithubApi
import com.filippo.repos.details.domain.RepositoryDataSource
import com.filippo.repos.details.domain.model.RepositoryId
import com.filippo.repos.network.ApiResponse
import com.filippo.repos.network.apiCall
import javax.inject.Inject

internal class RepositoryDataSourceImpl @Inject constructor(
    private val api: GithubApi,
) : RepositoryDataSource {

    override suspend fun getRepositoryId(
        repositoryOwner: String,
        repository: String,
    ): ApiResponse<RepositoryId> =
        apiCall {
            api.getRepository(
                repositoryOwner,
                repository
            ).id.let(::RepositoryId)
        }
}
