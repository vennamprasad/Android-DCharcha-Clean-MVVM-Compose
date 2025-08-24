package com.dcharcha.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * App theme that can:
 *  - use your Brand palette (default), or
 *  - adopt Dynamic Color on Android 12+ if enabled in settings.
 */
@Composable
fun DCharchaTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    useDynamicColor: Boolean = false, // flip from DataStore setting if you like
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colorScheme =
        if (useDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (useDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        } else {
            if (useDarkTheme) DCharchaDarkColors else DCharchaLightColors
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = DCharchaTypography,
        shapes = DCharchaShapes,
        content = content
    )
}