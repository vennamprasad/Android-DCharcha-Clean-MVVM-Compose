package com.dcharcha.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface FeatureNavGraph {
    val route: String
    fun isEnabled(): Boolean = true
    fun NavGraphBuilder.build(navController: NavHostController)
}
