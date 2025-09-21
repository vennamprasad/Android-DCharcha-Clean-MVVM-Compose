package com.dcharcha.core.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable

@Composable
fun dCharchaShapes(): Shapes {
    val dimens = MaterialTheme.dimens
    return Shapes(
        extraSmall = RoundedCornerShape(dimens.cornerXS),
        small = RoundedCornerShape(dimens.cornerS),
        medium = RoundedCornerShape(dimens.cornerM),
        large = RoundedCornerShape(dimens.cornerL),
        extraLarge = RoundedCornerShape(dimens.cornerXL)
    )
}