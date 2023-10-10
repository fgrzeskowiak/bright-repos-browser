package com.filippo.repos.search.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    SearchContent(
        recentSearches = state.recentSearches,
        error = state.error,
        onSubmitClicked = viewModel::submit
    )
}

@Composable
private fun SearchContent(
    recentSearches: List<String>,
    error: String?,
    onSubmitClicked: (String) -> Unit,
) {
    val (input, setInput) = remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = input,
                onValueChange = setInput,
                isError = error != null,
                supportingText = error?.let {
                    { Text(error) }
                },
            )
            Text(text = "Recent searches")
            recentSearches.forEach {
                Text(
                    modifier = Modifier.clickable { onSubmitClicked(it) },
                    text = it
                )
            }
            Button(onClick = { onSubmitClicked(input) }) {
                Text(text = "Submit")
            }
        }
    }
}
