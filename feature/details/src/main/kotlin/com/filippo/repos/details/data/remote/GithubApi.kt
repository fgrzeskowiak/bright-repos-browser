package com.filippo.repos.details.data.remote

import com.filippo.repos.details.data.remote.model.CommitResponse
import com.filippo.repos.details.data.remote.model.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface GithubApi {
    @GET("repos/{owner}/{repo}/commits")
    suspend fun getCommits(
        @Path("owner") owner: String,
        @Path("repo") repoName: String,
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 1,
    ): List<CommitResponse>

    @GET("repos/{owner}/{repo}")
    suspend fun getRepository(
        @Path("owner") owner: String,
        @Path("repo") name: String,
    ): RepositoryResponse
}
