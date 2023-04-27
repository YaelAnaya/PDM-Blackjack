package ic.yao.blackjack.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = badgeContent,
    onPrimary = white,
    onPrimaryContainer = lightBlue,
    secondary = badgeContainer,
    onSecondary = scoreTitleLabel,
    onSecondaryContainer = logoTint,
    tertiary = skipButtonDark,
    onTertiary = secondaryButtonDark,
    onTertiaryContainer = continueButtonDark,
    surface = cardHeadersDark,
    onSurface = cardBodyDark,
    background = darkBackground,
    onBackground = cardBackgroundDark,
)

private val LightColorScheme = lightColorScheme(
    primary = badgeContent,
    onPrimary = white,
    onPrimaryContainer = lightBlue,
    secondary = badgeContainer,
    onSecondary = scoreTitleLabel,
    onSecondaryContainer = logoTint,
    tertiary = skipButtonLight,
    onTertiary = secondaryButtonLight,
    onTertiaryContainer = continueButtonLight,
    surface = cardHeadersLight,
    onSurface = cardBodyLight,
    background = lightBackground,
    onBackground = cardBackgroundLight,
)

@Composable
fun BlackjackTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}