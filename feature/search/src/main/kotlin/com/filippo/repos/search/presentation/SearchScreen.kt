package com.filippo.repos.search.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.filippo.repos.common.TextResource
import com.filippo.repos.common.asString
import com.filippo.repos.search.R

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    SearchContent(
        recentSearches = state.recentSearches,
        error = state.error,
        onSearchClicked = viewModel::search
    )
}

@Composable
private fun SearchContent(
    recentSearches: List<String>,
    error: TextResource?,
    onSearchClicked: (String) -> Unit,
) {
    val (input, setInput) = remember { mutableStateOf("") }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                onClick = { onSearchClicked(input) }
            ) {
                Text(
                    text = stringResource(R.string.search_button),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        ) {
            Text(
                text = stringResource(R.string.search_repos),
                style = MaterialTheme.typography.titleLarge
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = input,
                onValueChange = setInput,
                isError = error != null,
                supportingText = error?.let {
                    { Text(error.asString()) }
                },
                placeholder = { Text(text = stringResource(R.string.search_hint)) },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = { onSearchClicked(input) }
                )
            )
            if (recentSearches.isNotEmpty()) {
                Text(
                    text = stringResource(R.string.recent_searches),
                    style = MaterialTheme.typography.titleMedium
                )
                LazyColumn {
                    items(recentSearches) {
                        Text(
                            modifier = Modifier
                                .clickable { onSearchClicked(it) }
                                .padding(8.dp),
                            text = it,
                            style = MaterialTheme.typography.titleSmall,
                        )
                    }
                }
            }
        }
    }
}
