package com.dcharcha.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dcharcha.core.ui.components.shimmerish

@Composable
fun ItemPlaceholderCard() {
    // Simple placeholder without external libs
    ListItem(
        headlineContent = {
            Box(
                Modifier
                    .height(18.dp)
                    .fillMaxWidth(0.5f)
                    .shimmerish()
            )
        },
        supportingContent = {
            Box(
                Modifier
                    .height(14.dp)
                    .fillMaxWidth(0.8f)
                    .shimmerish()
            )
        }
    )
    HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
}
