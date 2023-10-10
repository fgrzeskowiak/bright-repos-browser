package com.filippo.repos.details.data

import com.filippo.repos.database.model.CommitEntity
import com.filippo.repos.details.domain.model.Commit
import com.filippo.repos.details.domain.model.Sha

internal fun List<CommitEntity>.toDomain(): List<Commit> = map { entity ->
    Commit(
        author = entity.author,
        sha = Sha(entity.sha),
        message = entity.message,
    )
}

internal fun List<Commit>.toEntity(repositoryName: String): List<CommitEntity> = map { commit ->
    CommitEntity(
        author = commit.author,
        sha = commit.sha.value,
        message = commit.message,
        repositoryName = repositoryName,
    )
}
