package com.filippo.repos.details.domain

import arrow.core.Either
import arrow.core.raise.either
import com.filippo.repos.details.domain.model.Repo
import com.filippo.repos.network.RequestError
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

internal class GetRepositoryUseCase @Inject constructor(
    private val commitsDataSource: CommitsDataSource,
    private val repositoryDataSource: RepositoryDataSource,
) {
    suspend operator fun invoke(repoOwner: String, repoName: String): Either<RequestError, Repo> =
        coroutineScope {
            either {
                val repoId = async { repositoryDataSource.getRepositoryId(repoOwner, repoName) }
                val commits = async { commitsDataSource.getCommits(repoOwner, repoName) }

                Repo(
                    id = repoId.await().bind(),
                    commits = commits.await().bind()
                )
            }
        }
}
