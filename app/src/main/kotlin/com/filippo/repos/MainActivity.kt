package com.filippo.repos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.filippo.repos.navigation.domain.NavigationCommand
import com.filippo.repos.navigation.domain.Navigator
import com.filippo.repos.theme.BrightReposBrowserTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            CollectNavigationCommands(navController)
            BrightReposBrowserTheme {
                BrightReposNavigation(navController)
            }
        }
    }

    @Composable
    private fun CollectNavigationCommands(navController: NavHostController) {
        LaunchedEffect(Unit) {
            navigator.navigationCommands.collectLatest { command ->
                when (command) {
                    is NavigationCommand.Destination -> navController.navigate(command.route)
                    NavigationCommand.NavigateUp -> navController.navigateUp()
                }
            }
        }
    }
}
