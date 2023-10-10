package com.filippo.repos.details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filippo.repos.details.domain.GetRepositoryUseCase
import com.filippo.repos.navigation.domain.REPOSITORY_NAME_ARG
import com.filippo.repos.navigation.domain.REPOSITORY_OWNER_ARG
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
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val refreshTrigger = MutableSharedFlow<Unit>()

    internal val state = refreshTrigger
        .onStart { emit(Unit) }
        .flatMapLatest { repoDetailsFlow }
        .stateIn(viewModelScope, SharingStarted.Lazily, RepoDetailsState())

    private val repoDetailsFlow = flow {
        emit(RepoDetailsState(isLoading = true))
        val (repoOwner, repoName) = getNavigationArgs()

        if (repoOwner.isNullOrEmpty() || repoName.isNullOrEmpty()) {
            emit(RepoDetailsState(errorMessage = "Bad arguments"))
        } else {
            emit(getRepoDetails(repoOwner, repoName).toViewState())
        }
    }

    private fun getNavigationArgs() = savedStateHandle.run {
        get<String>(REPOSITORY_OWNER_ARG) to get<String>(REPOSITORY_NAME_ARG)
    }
}
