package com.filippo.repos.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun BrightReposBrowserTheme(
    showDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (showDarkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}
