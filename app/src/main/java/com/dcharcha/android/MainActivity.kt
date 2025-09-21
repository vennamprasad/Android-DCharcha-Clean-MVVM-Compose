@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package com.dcharcha.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.dcharcha.android.ui.DCharchaApp
import com.dcharcha.core.ui.theme.DCharchaTheme
import dagger.hilt.android.AndroidEntryPoint
import android.graphics.Color as AColor

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1) Turn on edge‑to‑edge with automatic light/dark icon handling
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                /* lightScrim   = */ AColor.TRANSPARENT,
                /* darkScrim    = */ AColor.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.auto(
                /* lightScrim   = */ AColor.TRANSPARENT,
                /* darkScrim    = */ AColor.TRANSPARENT
            )
        )

        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            DCharchaTheme(
                windowSizeClass = windowSizeClass,
                useDynamicColor = true
            ) {
                DCharchaApp()
            }
        }
    }
}