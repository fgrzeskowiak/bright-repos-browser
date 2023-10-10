package com.filippo.repos.details.domain.model

data class Repo(
    val id: RepositoryId,
    val commits: List<Commit>,
)
