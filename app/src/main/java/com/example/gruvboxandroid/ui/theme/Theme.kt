package com.example.gruvboxandroid.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = gb_dark_primary,
    onPrimary = gb_dark_onPrimary,
    primaryContainer = gb_dark_primaryContainer,
    onPrimaryContainer = gb_dark_onPrimaryContainer,
    secondary = gb_dark_secondary,
    onSecondary = gb_dark_onSecondary,
    secondaryContainer = gb_dark_secondaryContainer,
    onSecondaryContainer = gb_dark_onSecondaryContainer,
    tertiary = gb_dark_tertiary,
    onTertiary = gb_dark_onTertiary,
    tertiaryContainer = gb_dark_tertiaryContainer,
    onTertiaryContainer = gb_dark_onTertiaryContainer,
    error = gb_dark_error,
    errorContainer = gb_dark_errorContainer,
    onError = gb_dark_onError,
    onErrorContainer = gb_dark_onErrorContainer,
    background = gb_dark_background,
    onBackground = gb_dark_onBackground,
    surface = gb_dark_surface,
    onSurface = gb_dark_onSurface,
    surfaceVariant = gb_dark_surfaceVariant,
    onSurfaceVariant = gb_dark_onSurfaceVariant,
    outline = gb_dark_outline,
    inverseOnSurface = gb_dark_inverseOnSurface,
    inverseSurface = gb_dark_inverseSurface,
    inversePrimary = gb_dark_inversePrimary,
    surfaceTint = gb_dark_surfaceTint,
    outlineVariant = gb_dark_outlineVariant,
    scrim = gb_dark_scrim,
    surfaceBright = gb_dark_surfaceBright,
    surfaceContainer = gb_dark_surfaceContainer,
    surfaceContainerHigh = gb_dark_surfaceContainerHigh,
    surfaceContainerHighest = gb_dark_surfaceContainerHighest,
    surfaceContainerLow = gb_dark_surfaceContainerLow,
    surfaceContainerLowest = gb_dark_surfaceContainerLowest,
    surfaceDim = gb_dark_surfaceDim,
)

private val LightColorScheme = lightColorScheme(
    primary = gb_light_primary,
    onPrimary = gb_light_onPrimary,
    primaryContainer = gb_light_primaryContainer,
    onPrimaryContainer = gb_light_onPrimaryContainer,
    secondary = gb_light_secondary,
    onSecondary = gb_light_onSecondary,
    secondaryContainer = gb_light_secondaryContainer,
    onSecondaryContainer = gb_light_onSecondaryContainer,
    tertiary = gb_light_tertiary,
    onTertiary = gb_light_onTertiary,
    tertiaryContainer = gb_light_tertiaryContainer,
    onTertiaryContainer = gb_light_onTertiaryContainer,
    error = gb_light_error,
    errorContainer = gb_light_errorContainer,
    onError = gb_light_onError,
    onErrorContainer = gb_light_onErrorContainer,
    background = gb_light_background,
    onBackground = gb_light_onBackground,
    surface = gb_light_surface,
    onSurface = gb_light_onSurface,
    surfaceVariant = gb_light_surfaceVariant,
    onSurfaceVariant = gb_light_onSurfaceVariant,
    outline = gb_light_outline,
    inverseOnSurface = gb_light_inverseOnSurface,
    inverseSurface = gb_light_inverseSurface,
    inversePrimary = gb_light_inversePrimary,
    surfaceTint = gb_light_surfaceTint,
    outlineVariant = gb_light_outlineVariant,
    scrim = gb_light_scrim,
    surfaceBright = gb_light_surfaceBright,
    surfaceContainer = gb_light_surfaceContainer,
    surfaceContainerHigh = gb_light_surfaceContainerHigh,
    surfaceContainerHighest = gb_light_surfaceContainerHighest,
    surfaceContainerLow = gb_light_surfaceContainerLow,
    surfaceContainerLowest = gb_light_surfaceContainerLowest,
    surfaceDim = gb_light_surfaceDim,
)

@Composable
fun GruvboxTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val view = LocalView.current

    SideEffect {
        val window = (view.context as Activity).window
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
    }

    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}