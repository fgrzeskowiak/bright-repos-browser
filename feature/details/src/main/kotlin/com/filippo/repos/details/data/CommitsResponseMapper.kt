package com.filippo.repos.details.data

import com.filippo.repos.details.data.remote.model.CommitResponse
import com.filippo.repos.details.domain.model.Commit
import com.filippo.repos.details.domain.model.Sha

internal fun List<CommitResponse>.toDomain(): List<Commit> = map { response ->
    Commit(
        author = response.commit.author.name,
        sha = Sha(response.sha),
        message = response.commit.message,
    )
}
