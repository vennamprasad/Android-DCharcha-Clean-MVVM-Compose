package com.dcharcha.core.helper

import android.app.Activity
import android.content.Context
import androidx.core.content.ContextCompat

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    else -> (this as? ContextCompat)?.let { null }
}