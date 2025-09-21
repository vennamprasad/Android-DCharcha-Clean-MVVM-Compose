package com.dcharcha.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dcharcha.core.ui.theme.dimens

@Composable
fun ProgressButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    loading: Boolean,
    enabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        modifier = modifier,
        enabled = enabled && !loading
    ) {
        Row(
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.space4),
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onPrimary,
                strokeWidth = MaterialTheme.dimens.space2,
                modifier = Modifier.size(MaterialTheme.dimens.space24)
            )
            content()
        }
    }
}

@Composable
@Preview
fun ProgressButtonPreview() {
    ProgressButton(onClick = {}, loading = false) {
        Text(text = "Progress Button")
    }
}