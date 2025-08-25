package com.dcharcha.feature.language

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dcharcha.core.localization.LocaleManager
import com.dcharcha.core.localization.popularLanguages
import com.dcharcha.core.ui.theme.AppIcon
import com.dcharcha.core.ui.theme.Icons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageRoute(onContinue: () -> Unit) {
    var selected by remember { mutableStateOf("en-IN") }
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Select Language") },
                    modifier = Modifier.fillMaxWidth(),
                )
            },
            bottomBar = {
                Button(
                    onClick = { LocaleManager.set(selected); onContinue() },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) { Text("Continue") }
            }
        ) { padding ->

            LazyColumn(
                Modifier.padding(padding)
            ) {
                items(popularLanguages) { lang ->
                    ListItem(
                        headlineContent = {
                            AppIcon(Icons.Language, modifier = Modifier.size(24.dp))
                        }, supportingContent = { Text(lang.label) },
                        trailingContent = {
                            RadioButton(selected = selected == lang.tag, onClick = null)
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selected = lang.tag }
                    )
                    HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
                }
            }
        }
}
