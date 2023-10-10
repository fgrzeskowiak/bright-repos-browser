package com.filippo.repos.details.domain.model

data class RepositoryWithCommits(
    val id: RepositoryId,
    val commits: List<Commit>,
)
