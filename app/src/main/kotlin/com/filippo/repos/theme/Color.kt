package com.filippo.repos.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val OrangeLight = Color(0xFFE9983E)
private val OrangeDark = Color(0xFFDB6E00)
private val PrimaryDark = Color(0xFF1E1E1E)

val DarkColorScheme = darkColorScheme(
    primary = OrangeLight,
    surface = PrimaryDark,
    onSurface = Color.White,
)

val LightColorScheme = lightColorScheme(
    primary = OrangeDark,
    surface = Color.White,
    onSurface = PrimaryDark,
)
