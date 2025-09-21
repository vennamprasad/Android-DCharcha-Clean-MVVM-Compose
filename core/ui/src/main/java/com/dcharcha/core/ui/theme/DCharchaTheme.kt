package com.dcharcha.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

/**
 * App theme that can:
 *  - use your Brand palette (default), or
 *  - adopt Dynamic Color on Android 12+ if enabled in settings.
 */
@Composable
fun DCharchaTheme(
    windowSizeClass: WindowSizeClass,
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    useDynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val ctx = LocalContext.current
    val colors =
        if (useDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (useDarkTheme) dynamicDarkColorScheme(ctx) else dynamicLightColorScheme(ctx)
        } else {
            if (useDarkTheme) DCharchaDarkColors else DCharchaLightColors
        }

    ProvideUiScales(windowSizeClass) {
        val typography = DCharchaTypography.withFont(
            family = Nunito,
            sizes = LocalFontSizes.current
        )
        MaterialTheme(
            colorScheme = colors,
            typography = typography,
            shapes = dCharchaShapes(),
            content = content
        )
    }
}

val MaterialTheme.dimens: DpScale @Composable get() = LocalDpScale.current
val MaterialTheme.fontSizes: FontSizes @Composable get() = LocalFontSizes.current

@Composable
fun ProvideUiScales(windowSizeClass: WindowSizeClass, content: @Composable () -> Unit) {
    val (dp, fonts) = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> PhoneDp to PhoneFonts           // phones
        WindowWidthSizeClass.Medium -> FoldableDp to FoldableFonts     // foldables / small tablets
        WindowWidthSizeClass.Expanded -> TabletDp to TabletFonts         // tablets
        else -> PhoneDp to PhoneFonts
    }
    CompositionLocalProvider(
        LocalDpScale provides dp,
        LocalFontSizes provides fonts,
        content = content
    )
}