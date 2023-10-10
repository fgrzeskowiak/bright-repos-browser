package com.filippo.repos.details.domain

import com.filippo.repos.details.domain.model.Repository
import com.filippo.repos.network.ApiResponse

internal interface RepositoryDataSource {
    suspend fun getRepository(
        owner: String,
        name: String,
    ): ApiResponse<Repository>
}
