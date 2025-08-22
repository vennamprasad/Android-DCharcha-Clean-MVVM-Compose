package com.dcharcha.feature.splash.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dcharcha.core.navigation.FeatureNavGraph
import com.dcharcha.core.navigation.StartDestinationProvider
import com.dcharcha.feature.splash.SplashRoute
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

private object Routes {
    const val Splash = "splash"
}

/** Public singleton object to avoid KSP issues with private/anonymous providers */
@Singleton
object SplashGraph : FeatureNavGraph, StartDestinationProvider {
    override val route: String = Routes.Splash
    override fun NavGraphBuilder.build(navController: NavHostController) {
        composable(Routes.Splash) {
            SplashRoute {
                navController.navigate("language") {
                    popUpTo(Routes.Splash) { inclusive = true }
                }
            }
        }
    }

    override val startRoute: String = Routes.Splash
}

@Module
@InstallIn(SingletonComponent::class)
object SplashNavBindings {
    @Provides
    @IntoSet
    @Singleton
    fun graph(): FeatureNavGraph = SplashGraph

    @Provides
    @IntoSet
    @Singleton
    fun start(): StartDestinationProvider = SplashGraph
}
