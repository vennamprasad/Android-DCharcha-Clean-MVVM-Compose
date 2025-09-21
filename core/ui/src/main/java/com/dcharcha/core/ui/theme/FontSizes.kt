package com.dcharcha.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp


@Immutable
data class FontSizes(
    val displayLarge: TextUnit,
    val displayMedium: TextUnit,
    val displaySmall: TextUnit,
    val headlineLarge: TextUnit,
    val headlineMedium: TextUnit,
    val headlineSmall: TextUnit,
    val titleLarge: TextUnit,
    val titleMedium: TextUnit,
    val titleSmall: TextUnit,
    val bodyLarge: TextUnit,
    val bodyMedium: TextUnit,
    val bodySmall: TextUnit,
    val labelLarge: TextUnit,
    val labelMedium: TextUnit,
    val labelSmall: TextUnit,
)

val PhoneFonts = FontSizes(
    displayLarge = 36.sp,
    displayMedium = 32.sp,
    displaySmall = 28.sp,
    headlineLarge = 24.sp,
    headlineMedium = 22.sp,
    headlineSmall = 20.sp,
    titleLarge = 18.sp,
    titleMedium = 16.sp,
    titleSmall = 14.sp,
    bodyLarge = 16.sp,
    bodyMedium = 14.sp,
    bodySmall = 12.sp,
    labelLarge = 14.sp,
    labelMedium = 12.sp,
    labelSmall = 11.sp
)

val FoldableFonts = FontSizes(
    displayLarge = 52.sp,
    displayMedium = 36.sp,
    displaySmall = 28.sp,
    headlineLarge = 28.sp,
    headlineMedium = 24.sp,
    headlineSmall = 22.sp,
    titleLarge = 22.sp,
    titleMedium = 18.sp,
    titleSmall = 16.sp,
    bodyLarge = 17.sp,
    bodyMedium = 15.sp,
    bodySmall = 13.sp,
    labelLarge = 13.sp,
    labelMedium = 12.sp,
    labelSmall = 11.sp
)

val TabletFonts = FontSizes(
    displayLarge = 56.sp,
    displayMedium = 40.sp,
    displaySmall = 32.sp,
    headlineLarge = 32.sp,
    headlineMedium = 28.sp,
    headlineSmall = 24.sp,
    titleLarge = 24.sp,
    titleMedium = 20.sp,
    titleSmall = 18.sp,
    bodyLarge = 18.sp,
    bodyMedium = 16.sp,
    bodySmall = 14.sp,
    labelLarge = 14.sp,
    labelMedium = 13.sp,
    labelSmall = 12.sp
)

val LocalFontSizes = staticCompositionLocalOf { PhoneFonts }