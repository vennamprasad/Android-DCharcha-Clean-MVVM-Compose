package com.dcharcha.core.localization

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

object LocaleManager {
    private val selected = MutableStateFlow("en-IN")

    fun current(): Flow<String> = selected

    fun set(tag: String) {
        selected.value = tag
        val locales = LocaleListCompat.forLanguageTags(tag)
        AppCompatDelegate.setApplicationLocales(locales)
    }
}
