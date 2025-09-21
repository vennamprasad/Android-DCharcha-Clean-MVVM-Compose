package com.dcharcha.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.dcharcha.core.ui.R

// If your website uses a webfont (e.g. Poppins/Inter/Noto/IBM Plex),
// add it via resource font and replace FontFamily.Default below.
val Nunito = FontFamily(
    Font(R.font.nunito_regular, FontWeight.Normal),
    Font(R.font.nunito_medium, FontWeight.Medium),
    Font(R.font.nunito_semi_bold, FontWeight.SemiBold),
    Font(R.font.nunito_bold, FontWeight.Bold),
    Font(R.font.nunito_extra_bold, FontWeight.ExtraBold),
    Font(R.font.nunito_black, FontWeight.Black),
    Font(R.font.nunito_light, FontWeight.Light),
    Font(R.font.nunito_extra_light, FontWeight.ExtraLight),
    Font(R.font.nunito_italic, FontWeight.Normal),
    Font(R.font.nunito_bold_italic, FontWeight.Bold),
    Font(R.font.nunito_semi_bold_italic, FontWeight.SemiBold),
    Font(R.font.nunito_extra_bold_italic, FontWeight.ExtraBold),
    Font(R.font.nunito_black_italic, FontWeight.Black),
    Font(R.font.nunito_light_italic, FontWeight.Light),
)

fun Typography.withFont(
    family: FontFamily,
    sizes: FontSizes
): Typography = Typography(
    displayLarge = displayLarge.copy(fontFamily = family, fontSize = sizes.displayLarge),
    displayMedium = displayMedium.copy(fontFamily = family, fontSize = sizes.displayMedium),
    displaySmall = displaySmall.copy(fontFamily = family, fontSize = sizes.displaySmall),
    headlineLarge = headlineLarge.copy(fontFamily = family, fontSize = sizes.headlineLarge),
    headlineMedium = headlineMedium.copy(fontFamily = family, fontSize = sizes.headlineMedium),
    headlineSmall = headlineSmall.copy(fontFamily = family, fontSize = sizes.headlineSmall),
    titleLarge = titleLarge.copy(fontFamily = family, fontSize = sizes.titleLarge),
    titleMedium = titleMedium.copy(fontFamily = family, fontSize = sizes.titleMedium),
    titleSmall = titleSmall.copy(fontFamily = family, fontSize = sizes.titleSmall),
    bodyLarge = bodyLarge.copy(fontFamily = family, fontSize = sizes.bodyLarge),
    bodyMedium = bodyMedium.copy(fontFamily = family, fontSize = sizes.bodyMedium),
    bodySmall = bodySmall.copy(fontFamily = family, fontSize = sizes.bodySmall),
    labelLarge = labelLarge.copy(fontFamily = family, fontSize = sizes.labelLarge),
    labelMedium = labelMedium.copy(fontFamily = family, fontSize = sizes.labelMedium),
    labelSmall = labelSmall.copy(fontFamily = family, fontSize = sizes.labelSmall),
)

private fun Typography.withFontFamily(fontFamily: FontFamily): Typography = Typography(
    displayLarge = displayLarge.copy(fontFamily = fontFamily),
    displayMedium = displayMedium.copy(fontFamily = fontFamily),
    displaySmall = displaySmall.copy(fontFamily = fontFamily),
    headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
    headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
    headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
    titleLarge = titleLarge.copy(fontFamily = fontFamily),
    titleMedium = titleMedium.copy(fontFamily = fontFamily),
    titleSmall = titleSmall.copy(fontFamily = fontFamily),
    bodyLarge = bodyLarge.copy(fontFamily = fontFamily),
    bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
    bodySmall = bodySmall.copy(fontFamily = fontFamily),
    labelLarge = labelLarge.copy(fontFamily = fontFamily),
    labelMedium = labelMedium.copy(fontFamily = fontFamily),
    labelSmall = labelSmall.copy(fontFamily = fontFamily),
)

val DCharchaTypography = Typography().withFontFamily(Nunito)