package com.filippo.repos.details.data

import com.filippo.repos.details.data.remote.GithubApi
import com.filippo.repos.details.domain.CommitsDataSource
import com.filippo.repos.details.domain.model.Commit
import com.filippo.repos.network.ApiResponse
import com.filippo.repos.network.apiCall
import javax.inject.Inject

internal class CommitsDataSourceImpl @Inject constructor(
    private val api: GithubApi,
) : CommitsDataSource {

    override suspend fun getCommits(
        repositoryOwner: String,
        repositoryName: String,
    ): ApiResponse<List<Commit>> =
        apiCall { api.getCommits(repositoryOwner, repositoryName).toDomain() }
}
