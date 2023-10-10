package com.filippo.repos.details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filippo.repos.details.domain.GetRepositoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class RepoDetailsViewModel @Inject internal constructor(
    private val getRepoDetails: GetRepositoryUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val args = savedStateHandle.get<String>("nav_args") ?: "bright/shouldko"

    private val refreshTrigger = MutableSharedFlow<Unit>()

    internal val state = refreshTrigger
        .onStart { emit(Unit) }
        .flatMapLatest { repoDetailsFlow }
        .stateIn(viewModelScope, SharingStarted.Lazily, RepoDetailsState())

    private val repoDetailsFlow = flow {
        emit(RepoDetailsState(isLoading = true))
        val (repoOwner, repoName) = args.split('/')
        emit(getRepoDetails(repoOwner, repoName).toViewState())
    }
}
