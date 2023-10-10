package com.filippo.repos.navigation.domain

import kotlinx.coroutines.flow.SharedFlow

interface Navigator {
    val navigationCommands: SharedFlow<NavigationCommand>
    fun openDetails(repositoryOwner: String, repositoryName: String)
    fun navigateUp()
}
