package com.dcharcha.android.ui.nav

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppNavViewModel @Inject constructor(
    registry: AppGraphRegistry
) : ViewModel() {
    val entries = registry.enabledEntries()
    val start = registry.startRoute("splash")
}
