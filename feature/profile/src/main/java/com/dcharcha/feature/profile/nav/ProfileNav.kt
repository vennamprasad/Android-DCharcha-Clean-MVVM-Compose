package com.dcharcha.feature.profile.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dcharcha.core.navigation.FeatureNavGraph
import com.dcharcha.feature.profile.ProfileSetupRoute
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

private object Routes {
    const val Setup = "profile/setup"
}

private class ProfileGraph : FeatureNavGraph {
    override val route: String = "profile"
    override fun NavGraphBuilder.build(navController: NavHostController) {
        composable(Routes.Setup) {
            ProfileSetupRoute(onDone = {
                navController.navigate("home") {
                    popUpTo(0) { inclusive = true }
                }
            })
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
object ProfileNavBindings {
    @Provides
    @IntoSet
    @Singleton
    fun graph(): FeatureNavGraph = ProfileGraph()
}
