package com.filippo.repos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.filippo.repos.details.presentation.RepoDetailsScreen
import com.filippo.repos.theme.BrightReposBrowserTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            BrightReposBrowserTheme {
                NavHost(navController = navController, startDestination = "details") {
                    composable("details") {
                        RepoDetailsScreen()
                    }
                }
            }
        }
    }
}
