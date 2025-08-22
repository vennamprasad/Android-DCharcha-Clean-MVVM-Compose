package com.dcharcha.core.localization

data class LocaleOption(val tag: String, val label: String)

val popularLanguages = listOf(
    LocaleOption("en-IN", "English (India)"),
    LocaleOption("hi-IN", "हिन्दी"),
    LocaleOption("bn-IN", "বাংলা"),
    LocaleOption("te-IN", "తెలుగు"),
    LocaleOption("mr-IN", "मराठी"),
    LocaleOption("ta-IN", "தமிழ்"),
    LocaleOption("gu-IN", "ગુજરાતી"),
    LocaleOption("kn-IN", "ಕನ್ನಡ"),
    LocaleOption("ml-IN", "മലയാളം"),
    LocaleOption("or-IN", "ଓଡ଼ିଆ"),
    LocaleOption("pa-IN", "ਪੰਜਾਬੀ"),
    LocaleOption("ur-IN", "اردو"),
)
