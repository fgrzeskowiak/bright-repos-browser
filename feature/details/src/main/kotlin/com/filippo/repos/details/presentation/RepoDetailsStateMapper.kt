package com.filippo.repos.details.presentation

import arrow.core.Either
import com.filippo.repos.common.RequestError
import com.filippo.repos.common.errorMessage
import com.filippo.repos.details.domain.model.RepositoryWithCommits

internal fun Either<RequestError, RepositoryWithCommits>.toViewState(
    repoOwner: String,
    repoName: String,
): RepoDetailsState = fold(
    ifLeft = { RepoDetailsState(errorMessage = it.errorMessage) },
    ifRight = { repo ->
        RepoDetailsState(
            repoName = "$repoOwner/$repoName",
            content = RepoDetailsState.Content(
                repoId = repo.id.value,
                commits = repo.commits.map {
                    RepoDetailsState.Commit(
                        author = it.author,
                        sha = it.sha.shortValue,
                        message = it.message,
                    )
                }
            )
        )
    },
)
