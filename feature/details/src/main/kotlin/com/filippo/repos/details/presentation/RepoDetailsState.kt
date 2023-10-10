package com.filippo.repos.details.presentation

internal data class RepoDetailsState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val content: Content? = null,
) {
    data class Content(
        val repoId: String,
        val commits: List<String>,
    )
}
