package com.filippo.repos.details

import com.filippo.repos.database.model.CommitEntity
import com.filippo.repos.database.model.RepositoryEntity
import com.filippo.repos.details.data.remote.model.CommitResponse
import com.filippo.repos.details.data.remote.model.RepositoryResponse
import com.filippo.repos.details.domain.model.Commit
import com.filippo.repos.details.domain.model.Repository
import com.filippo.repos.details.domain.model.RepositoryId
import com.filippo.repos.details.domain.model.RepositoryWithCommits
import com.filippo.repos.details.domain.model.Sha

internal const val repositoryId = "id"
internal const val repositoryOwner = "owner"
internal const val repositoryName = "name"
internal const val author = "author"
internal const val message = "message"
internal const val sha = "sha"

internal val commitsResponse = listOf(
    CommitResponse(
        commit = CommitResponse.Commit(
            author = CommitResponse.Commit.Author(author),
            message = message
        ),
        sha = sha
    )
)
internal val commits = listOf(
    Commit(
        author = author,
        sha = Sha(sha),
        message = message
    )
)
internal val commitsEntity = listOf(
    CommitEntity(
        repositoryName = repositoryName,
        sha = sha,
        message = message,
        author = author
    )
)

internal val repository = Repository(
    id = RepositoryId(repositoryId),
    name = repositoryName,
    owner = repositoryOwner
)
internal val repositoryEntity = RepositoryEntity(
    id = repositoryId,
    name = repositoryName,
    owner = repositoryOwner
)
internal val repositoryResponse = RepositoryResponse(
    id = repositoryId,
    name = repositoryName,
    owner = RepositoryResponse.Owner(repositoryOwner)
)

internal val repositoryWithCommits = RepositoryWithCommits(
    id = RepositoryId(repositoryId),
    commits = commits,
)
