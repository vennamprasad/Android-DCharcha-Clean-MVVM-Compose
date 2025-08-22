package com.dcharcha.feature.auth.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.dcharcha.core.navigation.FeatureNavGraph
import com.dcharcha.feature.auth.AuthRoute
import com.dcharcha.feature.auth.ForgotRoute
import com.dcharcha.feature.auth.OtpRoute
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

private object Routes {
    const val Graph = "auth"
    const val Root = "auth/root"
    const val Otp = "auth/otp/{phone}"
    const val Forgot = "auth/forgot"
}

private class AuthGraph : FeatureNavGraph {
    override val route: String = Routes.Graph
    override fun NavGraphBuilder.build(navController: NavHostController) {
        navigation(startDestination = Routes.Root, route = Routes.Graph) {
            composable(Routes.Root) {
                AuthRoute(
                    onMobileOtp = { phone -> navController.navigate("auth/otp/$phone") },
                    onEmailLogin = { navController.navigate("profile/setup") { popUpTo(0) } },
                    onSignup = { /* TODO */ },
                    onForgot = { navController.navigate(Routes.Forgot) }
                )
            }
            composable(Routes.Forgot) { ForgotRoute { navController.popBackStack() } }
            composable(
                Routes.Otp,
                arguments = listOf(navArgument("phone") { type = NavType.StringType })
            ) { backStack ->
                val phone = backStack.arguments?.getString("phone").orEmpty()
                OtpRoute(phone) { navController.navigate("profile/setup") { popUpTo(0) } }
            }
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
object AuthNavBindings {
    @Provides
    @IntoSet
    @Singleton
    fun graph(): FeatureNavGraph = AuthGraph()
}
