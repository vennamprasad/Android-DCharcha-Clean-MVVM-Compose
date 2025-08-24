package com.dcharcha.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dcharcha.android.ui.DCharchaApp
import com.dcharcha.core.ui.theme.DCharchaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DCharchaTheme(
                useDarkTheme = true,
                useDynamicColor = true
            ) {
                DCharchaApp()
            }
        }
    }
}
