package com.dcharcha.core.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Reusable gradient brushes; match your website hero/banner vibe
object BrandGradients {
    val PrimarySweep = Brush.sweepGradient(
        colors = listOf(BrandPrimary, BrandSecondary, BrandTertiary, BrandPrimary)
    )
    val Hero = Brush.linearGradient(
        colors = listOf(BrandPrimary.copy(alpha = 0.18f), Color.Transparent)
    )
    val DarkGlow = Brush.radialGradient(
        colors = listOf(BrandPrimaryDark.copy(alpha = 0.30f), Color.Transparent)
    )
}