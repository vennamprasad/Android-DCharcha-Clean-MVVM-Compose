package com.dcharcha.android.ui.nav

import com.dcharcha.core.navigation.FeatureNavGraph
import com.dcharcha.core.navigation.StartDestinationProvider
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class AppGraphRegistry @Inject constructor(
    private val entries: Set<@JvmSuppressWildcards FeatureNavGraph>,
    private val startProviders: Set<@JvmSuppressWildcards StartDestinationProvider>
) {
    fun enabledEntries(): List<FeatureNavGraph> = entries.filter { it.isEnabled() }
    fun startRoute(default: String = "splash"): String =
        startProviders.firstOrNull()?.startRoute ?: default
}
