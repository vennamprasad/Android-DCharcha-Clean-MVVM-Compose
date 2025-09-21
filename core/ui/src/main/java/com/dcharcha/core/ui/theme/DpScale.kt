package com.dcharcha.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class DpScale(
    // spacing
    val space2: Dp,
    val space4: Dp,
    val space6: Dp,
    val space8: Dp,
    val space12: Dp,
    val space16: Dp,
    val space20: Dp,
    val space24: Dp,
    val space28: Dp,
    val space32: Dp,
    val space40: Dp,
    val space48: Dp,
    // corners
    val cornerXS: Dp,
    val cornerS: Dp,
    val cornerM: Dp,
    val cornerL: Dp,
    val cornerXL: Dp,
    // icons
    val iconS: Dp,
    val iconM: Dp,
    val iconL: Dp,
    val iconXL: Dp,
    val iconXXL: Dp,
    // components
    val buttonHeight: Dp,
    val inputHeight: Dp,
    val fabSize: Dp,
    val divider: Dp,
    val elevationCard: Dp,
    val elevationRaised: Dp,
    // layout helpers
    val appBarHeight: Dp,
    val maxContentWidth: Dp,
)

val PhoneDp = DpScale(
    space2 = 2.dp,
    space4 = 4.dp,
    space6 = 6.dp,
    space8 = 8.dp,
    space12 = 12.dp,
    space16 = 16.dp,
    space20 = 20.dp,
    space24 = 24.dp,
    space28 = 28.dp,
    space32 = 32.dp,
    space40 = 40.dp,
    space48 = 48.dp,
    cornerXS = 8.dp,
    cornerS = 12.dp,
    cornerM = 16.dp,
    cornerL = 20.dp,
    cornerXL = 28.dp,
    iconS = 16.dp,
    iconM = 20.dp,
    iconL = 24.dp,
    iconXL = 32.dp,
    iconXXL = 40.dp,
    buttonHeight = 48.dp,
    inputHeight = 56.dp,
    fabSize = 56.dp,
    divider = 1.dp,
    elevationCard = 2.dp,
    elevationRaised = 6.dp,
    appBarHeight = 56.dp,
    maxContentWidth = 840.dp
)

val FoldableDp = DpScale(
    space2 = 3.dp,
    space4 = 6.dp,
    space6 = 8.dp,
    space8 = 10.dp,
    space12 = 14.dp,
    space16 = 20.dp,
    space20 = 24.dp,
    space24 = 28.dp,
    space28 = 32.dp,
    space32 = 36.dp,
    space40 = 48.dp,
    space48 = 56.dp,
    cornerXS = 10.dp,
    cornerS = 14.dp,
    cornerM = 18.dp,
    cornerL = 22.dp,
    cornerXL = 30.dp,
    iconS = 18.dp,
    iconM = 22.dp,
    iconL = 28.dp,
    iconXL = 36.dp,
    iconXXL = 44.dp,
    buttonHeight = 52.dp,
    inputHeight = 60.dp,
    fabSize = 60.dp,
    divider = 1.dp,
    elevationCard = 3.dp,
    elevationRaised = 8.dp,
    appBarHeight = 64.dp,
    maxContentWidth = 1000.dp
)

val TabletDp = DpScale(
    space2 = 4.dp,
    space4 = 8.dp,
    space6 = 10.dp,
    space8 = 12.dp,
    space12 = 16.dp,
    space16 = 24.dp,
    space20 = 28.dp,
    space24 = 32.dp,
    space28 = 36.dp,
    space32 = 40.dp,
    space40 = 56.dp,
    space48 = 64.dp,
    cornerXS = 12.dp,
    cornerS = 16.dp,
    cornerM = 20.dp,
    cornerL = 24.dp,
    cornerXL = 32.dp,
    iconS = 20.dp,
    iconM = 24.dp,
    iconL = 32.dp,
    iconXL = 40.dp,
    iconXXL = 48.dp,
    buttonHeight = 56.dp,
    inputHeight = 64.dp,
    fabSize = 64.dp,
    divider = 1.dp,
    elevationCard = 4.dp,
    elevationRaised = 10.dp,
    appBarHeight = 72.dp,
    maxContentWidth = 1200.dp
)

val LocalDpScale = staticCompositionLocalOf { PhoneDp }