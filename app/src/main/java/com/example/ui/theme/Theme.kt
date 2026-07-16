package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
    primary = AccentTeal,
    secondary = AccentPurple,
    tertiary = AccentTeal,
    background = Slate900,
    surface = DarkSurface,
    onPrimary = Slate900,
    onSecondary = Slate900,
    onBackground = Slate100,
    onSurface = Slate100,
    surfaceVariant = Slate800,
    onSurfaceVariant = Slate300
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = true, // Force dark theme for the portfolio vibe
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme = DarkColorScheme
  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
