package fr.emajasekova.thegreatestcocktailapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val AppColorScheme = darkColorScheme(
    primary = AppCyan,
    onPrimary = AppBlack,
    primaryContainer = AppCyanContainer,
    onPrimaryContainer = AppCyan,
    secondary = AppPink,
    onSecondary = AppBlack,
    secondaryContainer = AppPinkContainer,
    onSecondaryContainer = AppPink,
    background = AppBlack,
    onBackground = AppWhite,
    surface = AppSurface,
    onSurface = AppWhite,
    surfaceVariant = AppSurfaceVariant,
    onSurfaceVariant = AppTextSecondary,
)

@Composable
fun TheGreatestCocktailAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = Typography,
        content = content
    )
}
