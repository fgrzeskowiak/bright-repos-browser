package com.filippo.repos.navigation.domain

sealed interface NavigationCommand {
    object NavigateUp : NavigationCommand
    data class Destination(val route: String) : NavigationCommand
    data class ShareOutside(val message: String) : NavigationCommand
}
