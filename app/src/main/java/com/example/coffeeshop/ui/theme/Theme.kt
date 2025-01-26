package com.example.coffeeshop.ui.theme
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.coffeeshop.ui.theme.backgroundDark
import com.example.coffeeshop.ui.theme.backgroundDarkHighContrast
import com.example.coffeeshop.ui.theme.backgroundDarkMediumContrast
import com.example.coffeeshop.ui.theme.backgroundLight
import com.example.coffeeshop.ui.theme.backgroundLightHighContrast
import com.example.coffeeshop.ui.theme.backgroundLightMediumContrast
import com.example.coffeeshop.ui.theme.errorContainerDark
import com.example.coffeeshop.ui.theme.errorContainerDarkHighContrast
import com.example.coffeeshop.ui.theme.errorContainerDarkMediumContrast
import com.example.coffeeshop.ui.theme.errorContainerLight
import com.example.coffeeshop.ui.theme.errorContainerLightHighContrast
import com.example.coffeeshop.ui.theme.errorContainerLightMediumContrast
import com.example.coffeeshop.ui.theme.errorDark
import com.example.coffeeshop.ui.theme.errorDarkHighContrast
import com.example.coffeeshop.ui.theme.errorDarkMediumContrast
import com.example.coffeeshop.ui.theme.errorLight
import com.example.coffeeshop.ui.theme.errorLightHighContrast
import com.example.coffeeshop.ui.theme.errorLightMediumContrast
import com.example.coffeeshop.ui.theme.inverseOnSurfaceDark
import com.example.coffeeshop.ui.theme.inverseOnSurfaceDarkHighContrast
import com.example.coffeeshop.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.example.coffeeshop.ui.theme.inverseOnSurfaceLight
import com.example.coffeeshop.ui.theme.inverseOnSurfaceLightHighContrast
import com.example.coffeeshop.ui.theme.inverseOnSurfaceLightMediumContrast
import com.example.coffeeshop.ui.theme.inversePrimaryDark
import com.example.coffeeshop.ui.theme.inversePrimaryDarkHighContrast
import com.example.coffeeshop.ui.theme.inversePrimaryDarkMediumContrast
import com.example.coffeeshop.ui.theme.inversePrimaryLight
import com.example.coffeeshop.ui.theme.inversePrimaryLightHighContrast
import com.example.coffeeshop.ui.theme.inversePrimaryLightMediumContrast
import com.example.coffeeshop.ui.theme.inverseSurfaceDark
import com.example.coffeeshop.ui.theme.inverseSurfaceDarkHighContrast
import com.example.coffeeshop.ui.theme.inverseSurfaceDarkMediumContrast
import com.example.coffeeshop.ui.theme.inverseSurfaceLight
import com.example.coffeeshop.ui.theme.inverseSurfaceLightHighContrast
import com.example.coffeeshop.ui.theme.inverseSurfaceLightMediumContrast
import com.example.coffeeshop.ui.theme.onBackgroundDark
import com.example.coffeeshop.ui.theme.onBackgroundDarkHighContrast
import com.example.coffeeshop.ui.theme.onBackgroundDarkMediumContrast
import com.example.coffeeshop.ui.theme.onBackgroundLight
import com.example.coffeeshop.ui.theme.onBackgroundLightHighContrast
import com.example.coffeeshop.ui.theme.onBackgroundLightMediumContrast
import com.example.coffeeshop.ui.theme.onErrorContainerDark
import com.example.coffeeshop.ui.theme.onErrorContainerDarkHighContrast
import com.example.coffeeshop.ui.theme.onErrorContainerDarkMediumContrast
import com.example.coffeeshop.ui.theme.onErrorContainerLight
import com.example.coffeeshop.ui.theme.onErrorContainerLightHighContrast
import com.example.coffeeshop.ui.theme.onErrorContainerLightMediumContrast
import com.example.coffeeshop.ui.theme.onErrorDark
import com.example.coffeeshop.ui.theme.onErrorDarkHighContrast
import com.example.coffeeshop.ui.theme.onErrorDarkMediumContrast
import com.example.coffeeshop.ui.theme.onErrorLight
import com.example.coffeeshop.ui.theme.onErrorLightHighContrast
import com.example.coffeeshop.ui.theme.onErrorLightMediumContrast
import com.example.coffeeshop.ui.theme.onPrimaryContainerDark
import com.example.coffeeshop.ui.theme.onPrimaryContainerDarkHighContrast
import com.example.coffeeshop.ui.theme.onPrimaryContainerDarkMediumContrast
import com.example.coffeeshop.ui.theme.onPrimaryContainerLight
import com.example.coffeeshop.ui.theme.onPrimaryContainerLightHighContrast
import com.example.coffeeshop.ui.theme.onPrimaryContainerLightMediumContrast
import com.example.coffeeshop.ui.theme.onPrimaryDark
import com.example.coffeeshop.ui.theme.onPrimaryDarkHighContrast
import com.example.coffeeshop.ui.theme.onPrimaryDarkMediumContrast
import com.example.coffeeshop.ui.theme.onPrimaryLight
import com.example.coffeeshop.ui.theme.onPrimaryLightHighContrast
import com.example.coffeeshop.ui.theme.onPrimaryLightMediumContrast
import com.example.coffeeshop.ui.theme.onSecondaryContainerDark
import com.example.coffeeshop.ui.theme.onSecondaryContainerDarkHighContrast
import com.example.coffeeshop.ui.theme.onSecondaryContainerDarkMediumContrast
import com.example.coffeeshop.ui.theme.onSecondaryContainerLight
import com.example.coffeeshop.ui.theme.onSecondaryContainerLightHighContrast
import com.example.coffeeshop.ui.theme.onSecondaryContainerLightMediumContrast
import com.example.coffeeshop.ui.theme.onSecondaryDark
import com.example.coffeeshop.ui.theme.onSecondaryDarkHighContrast
import com.example.coffeeshop.ui.theme.onSecondaryDarkMediumContrast
import com.example.coffeeshop.ui.theme.onSecondaryLight
import com.example.coffeeshop.ui.theme.onSecondaryLightHighContrast
import com.example.coffeeshop.ui.theme.onSecondaryLightMediumContrast
import com.example.coffeeshop.ui.theme.onSurfaceDark
import com.example.coffeeshop.ui.theme.onSurfaceDarkHighContrast
import com.example.coffeeshop.ui.theme.onSurfaceDarkMediumContrast
import com.example.coffeeshop.ui.theme.onSurfaceLight
import com.example.coffeeshop.ui.theme.onSurfaceLightHighContrast
import com.example.coffeeshop.ui.theme.onSurfaceLightMediumContrast
import com.example.coffeeshop.ui.theme.onSurfaceVariantDark
import com.example.coffeeshop.ui.theme.onSurfaceVariantDarkHighContrast
import com.example.coffeeshop.ui.theme.onSurfaceVariantDarkMediumContrast
import com.example.coffeeshop.ui.theme.onSurfaceVariantLight
import com.example.coffeeshop.ui.theme.onSurfaceVariantLightHighContrast
import com.example.coffeeshop.ui.theme.onSurfaceVariantLightMediumContrast
import com.example.coffeeshop.ui.theme.onTertiaryContainerDark
import com.example.coffeeshop.ui.theme.onTertiaryContainerDarkHighContrast
import com.example.coffeeshop.ui.theme.onTertiaryContainerDarkMediumContrast
import com.example.coffeeshop.ui.theme.onTertiaryContainerLight
import com.example.coffeeshop.ui.theme.onTertiaryContainerLightHighContrast
import com.example.coffeeshop.ui.theme.onTertiaryContainerLightMediumContrast
import com.example.coffeeshop.ui.theme.onTertiaryDark
import com.example.coffeeshop.ui.theme.onTertiaryDarkHighContrast
import com.example.coffeeshop.ui.theme.onTertiaryDarkMediumContrast
import com.example.coffeeshop.ui.theme.onTertiaryLight
import com.example.coffeeshop.ui.theme.onTertiaryLightHighContrast
import com.example.coffeeshop.ui.theme.onTertiaryLightMediumContrast
import com.example.coffeeshop.ui.theme.outlineDark
import com.example.coffeeshop.ui.theme.outlineDarkHighContrast
import com.example.coffeeshop.ui.theme.outlineDarkMediumContrast
import com.example.coffeeshop.ui.theme.outlineLight
import com.example.coffeeshop.ui.theme.outlineLightHighContrast
import com.example.coffeeshop.ui.theme.outlineLightMediumContrast
import com.example.coffeeshop.ui.theme.outlineVariantDark
import com.example.coffeeshop.ui.theme.outlineVariantDarkHighContrast
import com.example.coffeeshop.ui.theme.outlineVariantDarkMediumContrast
import com.example.coffeeshop.ui.theme.outlineVariantLight
import com.example.coffeeshop.ui.theme.outlineVariantLightHighContrast
import com.example.coffeeshop.ui.theme.outlineVariantLightMediumContrast
import com.example.coffeeshop.ui.theme.primaryContainerDark
import com.example.coffeeshop.ui.theme.primaryContainerDarkHighContrast
import com.example.coffeeshop.ui.theme.primaryContainerDarkMediumContrast
import com.example.coffeeshop.ui.theme.primaryContainerLight
import com.example.coffeeshop.ui.theme.primaryContainerLightHighContrast
import com.example.coffeeshop.ui.theme.primaryContainerLightMediumContrast
import com.example.coffeeshop.ui.theme.primaryDark
import com.example.coffeeshop.ui.theme.primaryDarkHighContrast
import com.example.coffeeshop.ui.theme.primaryDarkMediumContrast
import com.example.coffeeshop.ui.theme.primaryLight
import com.example.coffeeshop.ui.theme.primaryLightHighContrast
import com.example.coffeeshop.ui.theme.primaryLightMediumContrast
import com.example.coffeeshop.ui.theme.scrimDark
import com.example.coffeeshop.ui.theme.scrimDarkHighContrast
import com.example.coffeeshop.ui.theme.scrimDarkMediumContrast
import com.example.coffeeshop.ui.theme.scrimLight
import com.example.coffeeshop.ui.theme.scrimLightHighContrast
import com.example.coffeeshop.ui.theme.scrimLightMediumContrast
import com.example.coffeeshop.ui.theme.secondaryContainerDark
import com.example.coffeeshop.ui.theme.secondaryContainerDarkHighContrast
import com.example.coffeeshop.ui.theme.secondaryContainerDarkMediumContrast
import com.example.coffeeshop.ui.theme.secondaryContainerLight
import com.example.coffeeshop.ui.theme.secondaryContainerLightHighContrast
import com.example.coffeeshop.ui.theme.secondaryContainerLightMediumContrast
import com.example.coffeeshop.ui.theme.secondaryDark
import com.example.coffeeshop.ui.theme.secondaryDarkHighContrast
import com.example.coffeeshop.ui.theme.secondaryDarkMediumContrast
import com.example.coffeeshop.ui.theme.secondaryLight
import com.example.coffeeshop.ui.theme.secondaryLightHighContrast
import com.example.coffeeshop.ui.theme.secondaryLightMediumContrast
import com.example.coffeeshop.ui.theme.surfaceBrightDark
import com.example.coffeeshop.ui.theme.surfaceBrightDarkHighContrast
import com.example.coffeeshop.ui.theme.surfaceBrightDarkMediumContrast
import com.example.coffeeshop.ui.theme.surfaceBrightLight
import com.example.coffeeshop.ui.theme.surfaceBrightLightHighContrast
import com.example.coffeeshop.ui.theme.surfaceBrightLightMediumContrast
import com.example.coffeeshop.ui.theme.surfaceContainerDark
import com.example.coffeeshop.ui.theme.surfaceContainerDarkHighContrast
import com.example.coffeeshop.ui.theme.surfaceContainerDarkMediumContrast
import com.example.coffeeshop.ui.theme.surfaceContainerHighDark
import com.example.coffeeshop.ui.theme.surfaceContainerHighDarkHighContrast
import com.example.coffeeshop.ui.theme.surfaceContainerHighDarkMediumContrast
import com.example.coffeeshop.ui.theme.surfaceContainerHighLight
import com.example.coffeeshop.ui.theme.surfaceContainerHighLightHighContrast
import com.example.coffeeshop.ui.theme.surfaceContainerHighLightMediumContrast
import com.example.coffeeshop.ui.theme.surfaceContainerHighestDark
import com.example.coffeeshop.ui.theme.surfaceContainerHighestDarkHighContrast
import com.example.coffeeshop.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.example.coffeeshop.ui.theme.surfaceContainerHighestLight
import com.example.coffeeshop.ui.theme.surfaceContainerHighestLightHighContrast
import com.example.coffeeshop.ui.theme.surfaceContainerHighestLightMediumContrast
import com.example.coffeeshop.ui.theme.surfaceContainerLight
import com.example.coffeeshop.ui.theme.surfaceContainerLightHighContrast
import com.example.coffeeshop.ui.theme.surfaceContainerLightMediumContrast
import com.example.coffeeshop.ui.theme.surfaceContainerLowDark
import com.example.coffeeshop.ui.theme.surfaceContainerLowDarkHighContrast
import com.example.coffeeshop.ui.theme.surfaceContainerLowDarkMediumContrast
import com.example.coffeeshop.ui.theme.surfaceContainerLowLight
import com.example.coffeeshop.ui.theme.surfaceContainerLowLightHighContrast
import com.example.coffeeshop.ui.theme.surfaceContainerLowLightMediumContrast
import com.example.coffeeshop.ui.theme.surfaceContainerLowestDark
import com.example.coffeeshop.ui.theme.surfaceContainerLowestDarkHighContrast
import com.example.coffeeshop.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.example.coffeeshop.ui.theme.surfaceContainerLowestLight
import com.example.coffeeshop.ui.theme.surfaceContainerLowestLightHighContrast
import com.example.coffeeshop.ui.theme.surfaceContainerLowestLightMediumContrast
import com.example.coffeeshop.ui.theme.surfaceDark
import com.example.coffeeshop.ui.theme.surfaceDarkHighContrast
import com.example.coffeeshop.ui.theme.surfaceDarkMediumContrast
import com.example.coffeeshop.ui.theme.surfaceDimDark
import com.example.coffeeshop.ui.theme.surfaceDimDarkHighContrast
import com.example.coffeeshop.ui.theme.surfaceDimDarkMediumContrast
import com.example.coffeeshop.ui.theme.surfaceDimLight
import com.example.coffeeshop.ui.theme.surfaceDimLightHighContrast
import com.example.coffeeshop.ui.theme.surfaceDimLightMediumContrast
import com.example.coffeeshop.ui.theme.surfaceLight
import com.example.coffeeshop.ui.theme.surfaceLightHighContrast
import com.example.coffeeshop.ui.theme.surfaceLightMediumContrast
import com.example.coffeeshop.ui.theme.surfaceVariantDark
import com.example.coffeeshop.ui.theme.surfaceVariantDarkHighContrast
import com.example.coffeeshop.ui.theme.surfaceVariantDarkMediumContrast
import com.example.coffeeshop.ui.theme.surfaceVariantLight
import com.example.coffeeshop.ui.theme.surfaceVariantLightHighContrast
import com.example.coffeeshop.ui.theme.surfaceVariantLightMediumContrast
import com.example.coffeeshop.ui.theme.tertiaryContainerDark
import com.example.coffeeshop.ui.theme.tertiaryContainerDarkHighContrast
import com.example.coffeeshop.ui.theme.tertiaryContainerDarkMediumContrast
import com.example.coffeeshop.ui.theme.tertiaryContainerLight
import com.example.coffeeshop.ui.theme.tertiaryContainerLightHighContrast
import com.example.coffeeshop.ui.theme.tertiaryContainerLightMediumContrast
import com.example.coffeeshop.ui.theme.tertiaryDark
import com.example.coffeeshop.ui.theme.tertiaryDarkHighContrast
import com.example.coffeeshop.ui.theme.tertiaryDarkMediumContrast
import com.example.coffeeshop.ui.theme.tertiaryLight
import com.example.coffeeshop.ui.theme.tertiaryLightHighContrast
import com.example.coffeeshop.ui.theme.tertiaryLightMediumContrast

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      
      darkTheme -> darkScheme
      else -> lightScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content
  )
}

