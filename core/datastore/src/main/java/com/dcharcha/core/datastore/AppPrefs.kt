package com.dcharcha.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppPrefs @Inject constructor(@ApplicationContext private val ctx: Context) {
    private val Context.dataStore by preferencesDataStore("app_prefs")
    private val KEY_LANG_SET = booleanPreferencesKey("lang_set")
    private val KEY_ONBOARD_DONE = booleanPreferencesKey("onboard_done")

    val hasChosenLanguage = ctx.dataStore.data.map { it[KEY_LANG_SET] ?: false }
    val onboardingDone = ctx.dataStore.data.map { it[KEY_ONBOARD_DONE] ?: false }

    suspend fun setLanguageChosen() = ctx.dataStore.edit { it[KEY_LANG_SET] = true }
    suspend fun setOnboardingDone() = ctx.dataStore.edit { it[KEY_ONBOARD_DONE] = true }
}
