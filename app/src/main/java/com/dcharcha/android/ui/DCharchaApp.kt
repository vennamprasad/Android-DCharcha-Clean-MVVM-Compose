package com.dcharcha.android.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dcharcha.android.ui.nav.AppNavViewModel

@Composable
fun DCharchaApp() {
    val navController = rememberNavController()
    val vm: AppNavViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = vm.start) {
        vm.entries.forEach { entry -> with(entry) { build(navController) } }
    }
}
