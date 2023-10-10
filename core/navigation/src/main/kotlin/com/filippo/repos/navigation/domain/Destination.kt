package com.filippo.repos.navigation.domain

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Destination(
    val route: String,
    val arguments: List<NamedNavArgument>,
) {
    object Search : Destination(route = SEARCH_ROUTE, arguments = emptyList())
    object RepositoryDetails : Destination(
        route = REPOSITORY_DETAILS_ROUTE,
        arguments = listOf(
            navArgument(REPOSITORY_OWNER_ARG) {
                type = NavType.StringType
            },
            navArgument(REPOSITORY_NAME_ARG) {
                type = NavType.StringType
            }
        )
    ) {
        fun buildRouteWithArguments(repositoryOwner: String, repositoryName: String) = buildString {
            append(REPOSITORY_DETAILS_BASE_ROUTE)
            append('?')
            append(REPOSITORY_OWNER_ARG)
            append('=')
            append(repositoryOwner)
            append('&')
            append(REPOSITORY_NAME_ARG)
            append('=')
            append(repositoryName)
        }
    }
}

const val REPOSITORY_NAME_ARG = "repositoryName"
const val REPOSITORY_OWNER_ARG = "repositoryOwner"

private const val SEARCH_ROUTE = "search"
private const val REPOSITORY_DETAILS_BASE_ROUTE = "details"
private const val REPOSITORY_DETAILS_ROUTE =
    "$REPOSITORY_DETAILS_BASE_ROUTE?$REPOSITORY_OWNER_ARG={$REPOSITORY_OWNER_ARG}&$REPOSITORY_NAME_ARG={$REPOSITORY_NAME_ARG}"
