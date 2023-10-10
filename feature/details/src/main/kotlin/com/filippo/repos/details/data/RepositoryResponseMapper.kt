package com.filippo.repos.details.data

import com.filippo.repos.details.data.remote.model.RepositoryResponse
import com.filippo.repos.details.domain.model.Repository
import com.filippo.repos.details.domain.model.RepositoryId

internal fun RepositoryResponse.toDomain(): Repository = Repository(
    id = RepositoryId(id),
    name = name,
    owner = owner.login
)
