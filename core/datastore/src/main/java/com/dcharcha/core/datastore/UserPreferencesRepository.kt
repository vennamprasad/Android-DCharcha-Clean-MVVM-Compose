package com.dcharcha.core.datastore

import androidx.datastore.core.DataStore
import com.dcharcha.core.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesRepository @Inject constructor(
    private val dataStore: DataStore<UserPreferences>,
) {
    val prefs: Flow<UserPreferences> = dataStore.data
    val accessToken: Flow<String> = dataStore.data.map { it.accessToken }
    val refreshToken: Flow<String> = dataStore.data.map { it.refreshToken }
    val isLoggedIn: Flow<Boolean> = dataStore.data.map { it.isLoggedIn }
    val userId: Flow<String> = dataStore.data.map { it.userId }
    val languageTag: Flow<String> = dataStore.data.map { it.languageTag }
    val isDarkTheme: Flow<Boolean> = dataStore.data.map { it.darkTheme }
    val isLightTheme: Flow<Boolean> = dataStore.data.map { !it.darkTheme }
    val isWelcomeCompleted: Flow<Boolean> = dataStore.data.map { it.isWelcomeMessageShown }

    suspend fun setLanguage(tag: String) {
        dataStore.updateData { it.toBuilder().setLanguageTag(tag).build() }
    }

    suspend fun setDarkTheme(enabled: Boolean) {
        dataStore.updateData { it.toBuilder().setDarkTheme(enabled).build() }
    }

    suspend fun setUserId(userId: String) {
        dataStore.updateData { it.toBuilder().setUserId(userId).build() }
    }

    suspend fun setWelcomeCompleted(isWelcomeShown: Boolean) {
        dataStore.updateData { it.toBuilder().setIsWelcomeMessageShown(isWelcomeShown).build() }
    }

    suspend fun setAuthTokens(accessToken: String, refreshToken: String) {
        dataStore.updateData {
            it.toBuilder()
                .setAccessToken(accessToken)
                .setRefreshToken(refreshToken)
                .setIsLoggedIn(true)
                .build()
        }
    }

    suspend fun clearAuthTokens() {
        dataStore.updateData {
            it.toBuilder()
                .setAccessToken("")
                .setRefreshToken("")
                .setIsLoggedIn(false)
                .setIsWelcomeMessageShown(false)
                .build()
        }
    }

    suspend fun clearPrefs() {
        dataStore.updateData {
            it.toBuilder()
                .setAccessToken("")
                .setRefreshToken("")
                .setIsLoggedIn(false)
                .setUserId("")
                .setLanguageTag("en")
                .setDarkTheme(false)
                .setIsWelcomeMessageShown(false)
                .build()
        }
    }
}
