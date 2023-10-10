package com.filippo.repos.details.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.filippo.repos.common.asString
import com.filippo.repos.details.R

@Composable
fun RepoDetailsScreen(viewModel: RepoDetailsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    RepositoryDetailsContent(
        state = state,
        onBackClick = viewModel::navigateUp,
        onSendClicked = viewModel::sendCommits
    )
}

@Composable
private fun RepositoryDetailsContent(
    state: RepoDetailsState,
    onBackClick: () -> Unit,
    onSendClicked: (List<RepoDetailsState.Commit>) -> Unit,
) {
    val selectedCommits = remember { mutableStateListOf<RepoDetailsState.Commit>() }

    Scaffold(
        topBar = {
            state.repoName?.let { repoName ->
                TopBar(title = repoName, onBackClick = onBackClick)
            }
        },
        bottomBar = {
            if (selectedCommits.isNotEmpty()) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    onClick = { onSendClicked(selectedCommits.toList()) }
                ) {
                    Text(
                        text = stringResource(R.string.send),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    ) { paddingValues ->
        if (state.isLoading) {
            ProgressBar()
        }

        state.errorMessage?.let { error ->
            ErrorMessage(message = error.asString())
        }

        state.content?.let {
            RepositoryDetails(
                modifier = Modifier.padding(paddingValues),
                repoId = it.repoId,
                commits = it.commits,
                selectedCommits = selectedCommits.toList(),
                onCommitClicked = { commit ->
                    if (selectedCommits.contains(commit)) {
                        selectedCommits.remove(commit)
                    } else {
                        selectedCommits.add(commit)
                    }
                }
            )
        }
    }
}

@Composable
private fun RepositoryDetails(
    modifier: Modifier,
    repoId: String,
    commits: List<RepoDetailsState.Commit>,
    selectedCommits: List<RepoDetailsState.Commit>,
    onCommitClicked: (RepoDetailsState.Commit) -> Unit,
) {
    Column(
        modifier = modifier.padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = stringResource(R.string.repo_id),
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = repoId, style = MaterialTheme.typography.titleMedium)
        }
        Text(text = stringResource(R.string.history), style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(commits) { commit ->
                val isSelected = selectedCommits.contains(commit)
                Commit(
                    commit = commit,
                    isSelected = isSelected,
                    onClick = { onCommitClicked(commit) },
                )
            }
        }
    }
}

@Composable
private fun Commit(
    commit: RepoDetailsState.Commit,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val backgroundColor = MaterialTheme.colorScheme.run { if (isSelected) primary else surface }
    val textColor = MaterialTheme.colorScheme.run { if (isSelected) surface else onSurface }

    Column(
        modifier = Modifier
            .background(color = backgroundColor)
            .clickable(onClick = onClick),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = commit.message,
            style = MaterialTheme.typography.bodyMedium,
            color = textColor,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.author),
                style = MaterialTheme.typography.bodyMedium,
                color = textColor,
            )
            Text(
                text = commit.author,
                style = MaterialTheme.typography.bodyMedium,
                color = textColor,
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.sha),
                style = MaterialTheme.typography.bodyMedium,
                color = textColor,
            )
            Text(
                text = commit.sha,
                style = MaterialTheme.typography.bodyMedium,
                color = textColor,
            )
        }
        Divider()
    }
}

@Composable
private fun TopBar(title: String, onBackClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = title, style = MaterialTheme.typography.titleLarge) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Navigate Back"
                )
            }
        }
    )
}


@Composable
private fun ProgressBar() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorMessage(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.titleLarge
        )
    }
}
