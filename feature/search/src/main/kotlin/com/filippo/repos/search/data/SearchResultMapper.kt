package com.filippo.repos.search.data

import com.filippo.repos.database.model.RepositoryEntity
import com.filippo.repos.search.domain.model.SearchResult

internal fun RepositoryEntity.toDomain(): SearchResult =
    SearchResult(repositoryOwner = owner, repositoryName = name)
