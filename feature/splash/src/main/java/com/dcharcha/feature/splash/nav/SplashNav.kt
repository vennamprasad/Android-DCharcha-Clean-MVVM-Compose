package com.dcharcha.feature.splash.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dcharcha.core.navigation.FeatureNavGraph
import com.dcharcha.core.navigation.StartDestinationProvider
import com.dcharcha.feature.splash.SplashRoute
import javax.inject.Singleton

/** Public singleton object to avoid KSP issues with private/anonymous providers */
@Singleton
object SplashGraph : FeatureNavGraph, StartDestinationProvider {
    override val route: String = Routes.SPLASH
    override fun NavGraphBuilder.build(navController: NavHostController) {
        composable(Routes.SPLASH) {
            SplashRoute {
                navController.navigate("language") {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }
            }
        }
    }

    override val startRoute: String = Routes.SPLASH
}