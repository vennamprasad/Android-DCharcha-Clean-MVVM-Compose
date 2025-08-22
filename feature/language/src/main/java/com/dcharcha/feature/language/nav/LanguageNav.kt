package com.dcharcha.feature.language.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dcharcha.core.navigation.FeatureNavGraph
import com.dcharcha.feature.language.LanguageRoute
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

private object Routes {
    const val Language = "language"
}

private class LanguageGraph : FeatureNavGraph {
    override val route: String = Routes.Language
    override fun NavGraphBuilder.build(navController: NavHostController) {
        composable(Routes.Language) {
            LanguageRoute {
                navController.navigate("onboarding") {
                    popUpTo(
                        Routes.Language
                    ) { inclusive = true }
                }
            }
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
object LanguageNavBindings {
    @Provides
    @IntoSet
    @Singleton
    fun graph(): FeatureNavGraph = LanguageGraph()
}
