package com.filippo.repos.details.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RepoDetailsScreen(viewModel: RepoDetailsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            }

            state.errorMessage?.let { error ->
                Text(text = error)
            }

            state.content?.let {
                RepoDetailsContent(repoId = it.repoId, commits = it.commits)
            }
        }
    }
}

@Composable
private fun RepoDetailsContent(
    repoId: String,
    commits: List<String>,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = repoId)
        Spacer(modifier = Modifier.height(16.dp))
        commits.forEach {
            Text(it)
        }
    }
}
