package com.filippo.repos.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import com.filippo.repos.navigation.domain.Navigator
import com.filippo.repos.search.domain.RecentSearchesDataSource
import com.filippo.repos.search.domain.SearchInputValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject internal constructor(
    private val navigator: Navigator,
    inputValidator: SearchInputValidator,
    recentSearchesDataSource: RecentSearchesDataSource,
) : ViewModel() {
    private val searchInput = MutableSharedFlow<String>()
    private val validationFlow = searchInput
        .mapLatest(inputValidator::validate)
        .shareIn(viewModelScope, SharingStarted.Lazily, replay = 1)

    private val recentSearchesFlow = flow {
        emit(recentSearchesDataSource.recentSearches())
    }

    init {
        validationFlow
            .map { it.getOrNull() }
            .filterNotNull()
            .onEach { (repositoryOwner, repositoryName) ->
                navigator.openDetails(repositoryOwner, repositoryName)
            }
            .launchIn(viewModelScope)
    }

    val state: StateFlow<SearchState> = combine(
        validationFlow.onStart { emit(Pair("", "").right()) },
        recentSearchesFlow
    ) { validation, recentSearches ->
        SearchState(
            recentSearches = recentSearches.map { "${it.repositoryOwner}/${it.repositoryName}" },
            error = validation.leftOrNull()?.toString()
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, SearchState())

    fun submit(input: String) {
        viewModelScope.launch {
            searchInput.emit(input)
        }
    }
}
