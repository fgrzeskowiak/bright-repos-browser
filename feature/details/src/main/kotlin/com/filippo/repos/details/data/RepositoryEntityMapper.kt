package com.filippo.repos.details.data

import com.filippo.repos.database.model.RepositoryEntity
import com.filippo.repos.details.domain.model.Repository
import com.filippo.repos.details.domain.model.RepositoryId

internal fun RepositoryEntity.toDomain(): Repository = Repository(
    id = RepositoryId(id),
    name = name,
    owner = owner
)

internal fun Repository.toEntity(): RepositoryEntity = RepositoryEntity(
    id = id.value,
    name = name,
    owner = owner
)
