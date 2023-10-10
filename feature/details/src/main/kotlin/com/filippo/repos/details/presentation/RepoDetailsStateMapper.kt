package com.filippo.repos.details.presentation

import arrow.core.Either
import com.filippo.repos.details.domain.model.RepositoryWithCommits
import com.filippo.repos.network.RequestError

internal fun Either<RequestError, RepositoryWithCommits>.toViewState(): RepoDetailsState = fold(
    ifLeft = { RepoDetailsState(errorMessage = it.toString()) },
    ifRight = { repo ->
        RepoDetailsState(
            content = RepoDetailsState.Content(
                repoId = repo.id.value,
                commits = repo.commits.map { it.sha.shortValue }
            )
        )
    },
)
