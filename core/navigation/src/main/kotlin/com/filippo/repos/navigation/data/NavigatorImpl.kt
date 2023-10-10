package com.filippo.repos.navigation.data

import com.filippo.repos.navigation.domain.Destination
import com.filippo.repos.navigation.domain.NavigationCommand
import com.filippo.repos.navigation.domain.Navigator
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow

internal class NavigatorImpl @Inject constructor() : Navigator {
    override val navigationCommands = MutableSharedFlow<NavigationCommand>(extraBufferCapacity = 1)

    override fun openDetails(repositoryOwner: String, repositoryName: String) {
        navigationCommands.tryEmit(
            NavigationCommand.Destination(
                Destination.RepositoryDetails.buildRouteWithArguments(
                    repositoryOwner,
                    repositoryName
                )
            )
        )
    }

    override fun navigateUp() {
        navigationCommands.tryEmit(NavigationCommand.NavigateUp)
    }
}
