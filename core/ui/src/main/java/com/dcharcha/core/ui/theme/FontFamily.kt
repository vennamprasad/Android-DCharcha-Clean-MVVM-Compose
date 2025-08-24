package com.dcharcha.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp

// If your website uses a webfont (e.g. Poppins/Inter/Noto/IBM Plex),
// add it via resource font and replace FontFamily.Default below.
private val BodyFamily = FontFamily.SansSerif // TODO: swap to your brand font

val DCharchaTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = BodyFamily, fontWeight = FontWeight.Bold, fontSize = 48.sp, lineHeight = 54.sp
    ).tightLines(),
    headlineLarge = TextStyle(
        fontFamily = BodyFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 38.sp
    ).tightLines(),
    titleLarge = TextStyle(
        fontFamily = BodyFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ).tightLines(),
    bodyLarge = TextStyle(
        fontFamily = BodyFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ).tightLines(),
    bodyMedium = TextStyle(
        fontFamily = BodyFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ).tightLines(),
    labelLarge = TextStyle(
        fontFamily = BodyFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 16.sp
    ).tightLines()
)

private fun TextStyle.tightLines(): TextStyle = this.copy(
    platformStyle = PlatformTextStyle(includeFontPadding = false),
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Proportional,
        trim = LineHeightStyle.Trim.Both
    )
)