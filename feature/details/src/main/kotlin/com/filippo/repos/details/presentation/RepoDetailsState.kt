package com.filippo.repos.details.presentation

import com.filippo.repos.common.TextResource

internal data class RepoDetailsState(
    val isLoading: Boolean = false,
    val errorMessage: TextResource? = null,
    val repoName: String? = null,
    val content: Content? = null,
) {
    data class Content(
        val repoId: String,
        val commits: List<Commit>,
    )

    data class Commit(
        val author: String,
        val sha: String,
        val message: String,
    )
}
