package com.dcharcha.core.localization

data class LocaleOption(val tag: String, val label: String)

val popularLanguages = listOf(
    LocaleOption("en-IN", "English (India)"),
    LocaleOption("hi-IN", "हिन्दी"),
    LocaleOption("te-IN", "తెలుగు"),
    LocaleOption("ta-IN", "தமிழ்"),
    LocaleOption("kn-IN", "ಕನ್ನಡ"),
    LocaleOption("ml-IN", "മലയാളം"),
)
