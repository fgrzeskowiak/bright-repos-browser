package com.filippo.repos

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.filippo.repos.details.presentation.RepoDetailsScreen
import com.filippo.repos.navigation.domain.Destination
import com.filippo.repos.search.presentation.SearchScreen

@Composable
fun BrightReposNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destination.Search.route
    ) {
        composable(Destination.Search.route) {
            SearchScreen()
        }
        composable(
            route = Destination.RepositoryDetails.route,
            arguments = Destination.RepositoryDetails.arguments,
        ) {
            RepoDetailsScreen()
        }
    }
}
