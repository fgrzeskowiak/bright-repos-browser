package com.filippo.repos.details.presentation

import javax.inject.Inject

internal class CommitsMessageFormatter @Inject constructor() {
    fun prepareMessage(commits: List<RepoDetailsState.Commit>): String = buildString {
        appendLine("Commits history:")
        commits.forEach { commit ->
            appendLine(commit.message)
            appendLine("Author: ${commit.author}")
            appendLine("SHA: ${commit.sha}")
            appendLine()
            appendLine(SEPARATOR)
            appendLine()
        }
    }
}

private const val SEPARATOR = "**********************"
