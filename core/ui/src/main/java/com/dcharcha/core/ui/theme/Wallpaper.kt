package com.dcharcha.core.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Dynamic brand wallpaper you can place behind screens.
 * Example: set in Scaffold background or as a top-level Box.
 */
@Composable
fun BrandWallpaper(
    modifier: Modifier = Modifier,
    dark: Boolean = false
) {
    val brush = if (dark) BrandGradients.DarkGlow else BrandGradients.Hero
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush)
    )
}