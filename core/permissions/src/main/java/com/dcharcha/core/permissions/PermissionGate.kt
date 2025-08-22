package com.dcharcha.core.permissions

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun PermissionGate(
    permission: String,
    title: String,
    message: String,
    onGranted: @Composable () -> Unit,
) {
    var granted by remember { mutableStateOf(false) }
    var shouldExplain by remember { mutableStateOf(false) }

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            granted = isGranted
            shouldExplain = !isGranted
        }

    LaunchedEffect(Unit) { launcher.launch(permission) }

    if (granted) {
        onGranted()
    } else if (shouldExplain) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text(title) },
            text = { Text(message) },
            confirmButton = { TextButton(onClick = { launcher.launch(permission) }) { Text("Allow") } },
            dismissButton = { TextButton(onClick = {}) { Text("Not now") } },
        )
    }
}
