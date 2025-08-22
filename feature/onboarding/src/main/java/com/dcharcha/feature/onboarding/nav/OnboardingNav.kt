package com.dcharcha.feature.onboarding.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dcharcha.core.navigation.FeatureNavGraph
import com.dcharcha.feature.onboarding.OnboardingRoute
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

private object Routes { const val Onboarding = "onboarding" }

private class OnboardingGraph : FeatureNavGraph {
    override val route: String = Routes.Onboarding
    override fun NavGraphBuilder.build(navController: NavHostController) {
        composable(Routes.Onboarding) { OnboardingRoute { navController.navigate("auth") { popUpTo(Routes.Onboarding) { inclusive = true } } } }
    }
}

@Module
@InstallIn(SingletonComponent::class)
object OnboardingNavBindings {
    @Provides @IntoSet @Singleton fun graph(): FeatureNavGraph = OnboardingGraph()
}
