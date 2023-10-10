package com.filippo.repos.details.domain

import arrow.core.Either
import arrow.core.raise.either
import com.filippo.repos.details.domain.model.RepositoryWithCommits
import com.filippo.repos.network.RequestError
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

internal class GetRepositoryUseCase @Inject constructor(
    private val commitsDataSource: CommitsDataSource,
    private val repositoryDataSource: RepositoryDataSource,
) {
    suspend operator fun invoke(
        repositoryOwner: String,
        repositoryName: String,
    ): Either<RequestError, RepositoryWithCommits> =
        coroutineScope {
            either {
                val repositoryRequest = async {
                    repositoryDataSource.getRepository(
                        owner = repositoryOwner,
                        name = repositoryName
                    )
                }
                val commitsRequest = async {
                    commitsDataSource.getCommits(
                        repositoryOwner = repositoryOwner,
                        repositoryName = repositoryName
                    )
                }

                RepositoryWithCommits(
                    id = repositoryRequest.await().bind().id,
                    commits = commitsRequest.await().bind()
                )
            }
        }
}
