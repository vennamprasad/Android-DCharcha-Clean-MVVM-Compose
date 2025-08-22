package com.dcharcha.feature.home.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dcharcha.core.navigation.FeatureNavGraph
import com.dcharcha.feature.home.HomeRoute
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

private object Routes {
    const val Home = "home"
}

private class HomeGraph : FeatureNavGraph {
    override val route: String = Routes.Home
    override fun NavGraphBuilder.build(navController: NavHostController) {
        composable(Routes.Home) { HomeRoute() }
    }
}

@Module
@InstallIn(SingletonComponent::class)
object HomeNavBindings {
    @Provides
    @IntoSet
    @Singleton
    fun graph(): FeatureNavGraph = HomeGraph()
}
