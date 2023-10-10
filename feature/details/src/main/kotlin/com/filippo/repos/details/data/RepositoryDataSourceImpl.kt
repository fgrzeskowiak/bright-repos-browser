package com.filippo.repos.details.data

import arrow.core.raise.either
import com.filippo.repos.database.dao.RepositoriesDao
import com.filippo.repos.details.data.remote.GithubApi
import com.filippo.repos.details.domain.RepositoryDataSource
import com.filippo.repos.details.domain.model.Repository
import com.filippo.repos.network.ApiResponse
import com.filippo.repos.network.apiCall
import javax.inject.Inject

internal class RepositoryDataSourceImpl @Inject constructor(
    private val api: GithubApi,
    private val dao: RepositoriesDao,
) : RepositoryDataSource {

    override suspend fun getRepository(
        owner: String,
        name: String,
    ): ApiResponse<Repository> = either {
        val localRepository = dao.getRepository(owner = owner, name = name)?.toDomain()

        localRepository ?: getRepositoryFromRemote(owner = owner, name = name)
            .onRight { dao.insert(it.toEntity()) }
            .bind()
    }

    private suspend fun getRepositoryFromRemote(
        owner: String,
        name: String,
    ): ApiResponse<Repository> = apiCall {
        api.getRepository(owner = owner, name = name).toDomain()
    }
}
