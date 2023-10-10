package com.filippo.repos.details.domain

import com.filippo.repos.details.domain.model.RepositoryId
import com.filippo.repos.network.ApiResponse

internal interface RepositoryDataSource {
    suspend fun getRepositoryId(
        repositoryOwner: String,
        repository: String,
    ): ApiResponse<RepositoryId>
}
