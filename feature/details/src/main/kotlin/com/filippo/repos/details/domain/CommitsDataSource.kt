package com.filippo.repos.details.domain

import com.filippo.repos.details.domain.model.Commit
import com.filippo.repos.network.ApiResponse

internal interface CommitsDataSource {
    suspend fun getCommits(
        repositoryOwner: String,
        repositoryName: String,
    ): ApiResponse<List<Commit>>
}
