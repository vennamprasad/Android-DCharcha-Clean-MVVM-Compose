package com.dcharcha.android.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dcharcha.android.ui.nav.AppNavViewModel

@Composable
fun DCharchaApp() {
    Scaffold(contentWindowInsets = WindowInsets(0)) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeContent)
                .padding(innerPadding)
        ) {
            val navController = rememberNavController()
            val vm: AppNavViewModel = hiltViewModel()
            NavHost(navController = navController, startDestination = vm.start) {
                vm.entries.forEach { entry -> with(entry) { build(navController) } }
            }
        }
    }
}