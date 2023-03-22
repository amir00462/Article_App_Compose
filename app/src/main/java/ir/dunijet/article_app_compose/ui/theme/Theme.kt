package ir.dunijet.article_app_compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = cPrimary,
    primaryVariant = cPrimary,
    secondary = cBlack
)

private val DarkColorPalette = lightColors(
    primary = cPrimary,
    primaryVariant = cPrimary,
    secondary = cWhite
)

@Composable
fun Article_App_ComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
