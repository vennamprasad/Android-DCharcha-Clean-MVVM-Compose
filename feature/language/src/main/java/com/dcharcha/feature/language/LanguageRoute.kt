package com.dcharcha.feature.language

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import com.dcharcha.core.localization.LocaleManager
import com.dcharcha.core.localization.popularLanguages
import com.dcharcha.core.ui.theme.AppIcon
import com.dcharcha.core.ui.theme.Icons
import com.dcharcha.core.ui.theme.dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageRoute(onContinue: () -> Unit) {
    var selected by remember { mutableStateOf("en-IN") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.select_language),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                // TopAppBar sits under status bar correctly when using window insets in root
                modifier = Modifier.fillMaxWidth()
            )
        },

        // Bottom bar pinned above navigation bar and using theme spacing & height tokens
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding() // keep above system nav
                    .padding(
                        start = MaterialTheme.dimens.space24,
                        end = MaterialTheme.dimens.space24,
                        bottom = MaterialTheme.dimens.space16
                    )
            ) {
                Button(
                    onClick = {
                        LocaleManager.set(selected)
                        onContinue()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(MaterialTheme.dimens.buttonHeight)
                ) {
                    Text("Continue")
                }
            }
        }
    ) { innerPadding ->
        // Content area should respect the scaffold inner padding and use theme spacing
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(
                    start = MaterialTheme.dimens.space16,
                    end = MaterialTheme.dimens.space16,
                    top = MaterialTheme.dimens.space12
                ),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.space8)
        ) {
            items(popularLanguages) { lang ->
                ListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            role = Role.RadioButton,
                            onClick = { selected = lang.tag }
                        ),
                    headlineContent = {
                        Text(text = lang.label)
                    },
                    leadingContent = {
                        AppIcon(
                            key = Icons.Language,
                            contentDescription = "Language icon",
                            modifier = Modifier.size(MaterialTheme.dimens.iconL)
                        )
                    },
                    trailingContent = {
                        RadioButton(
                            selected = (selected == lang.tag),
                            onClick = { selected = lang.tag }
                        )
                    }
                )

                // Divider using theme thickness token (dimens.divider is a Dp)
                HorizontalDivider(thickness = MaterialTheme.dimens.divider)
            }
        }
    }
}