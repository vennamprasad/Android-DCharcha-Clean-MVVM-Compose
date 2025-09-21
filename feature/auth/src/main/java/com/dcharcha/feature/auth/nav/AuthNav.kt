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


class AuthGraph : FeatureNavGraph {
    override val route: String = Routes.GRAPH

    override fun NavGraphBuilder.build(navController: NavHostController) {
        navigation(startDestination = Routes.ROOT, route = Routes.GRAPH) {
            composable(Routes.ROOT) {
                AuthRoute(
                    onMobileOtp = { phone ->
                        navController.navigate("auth/otp/$phone")
                    },
                    onEmailLogin = {
                        navController.navigate("profile/setup") { popUpTo(0) }
                    },
                    onSignup = {
                        navController.navigate("profile/setup") { popUpTo(0) }
                    },
                    onForgot = {
                        navController.navigate(Routes.FORGOT)
                    }
                )
            }
            composable(Routes.FORGOT) {
                ForgotRoute {
                    navController.popBackStack()
                }
            }
            composable(
                Routes.OTP, arguments = listOf(navArgument("phone") {
                    type = NavType.StringType
                })
            ) { backStack ->
                val phone = backStack.arguments?.getString("phone").orEmpty()
                OtpRoute(phone) {
                    navController.navigate("profile/setup") {
                        popUpTo(0)
                    }
                }
            }
        }
    }
}
