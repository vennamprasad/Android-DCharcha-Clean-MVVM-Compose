package com.dcharcha.feature.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dcharcha.domain.model.Item

@Composable
fun ItemCard(item: Item, onClick: () -> Unit) {
    ListItem(
        headlineContent = { Text(item.title) },
        supportingContent = { Text(item.subtitle) },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .padding(vertical = 2.dp),
        overlineContent = null,
        leadingContent = {
            Icon(
                imageVector = Icons.Default.Description,
                contentDescription = null
            )
        },
        trailingContent = {
            IconButton(onClick = onClick) {
                Icon(Icons.Default.ChevronRight, contentDescription = "Open")
            }
        }
    )
    HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
}