package com.dcharcha.feature.auth

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthRoute(
    onMobileOtp: (String) -> Unit,
    onEmailLogin: () -> Unit,
    onSignup: () -> Unit,
    onForgot: () -> Unit,
) {
    val viewModel: AuthViewModel = hiltViewModel()
    AuthScreen(
        viewModel, onMobileOtp, onEmailLogin, onSignup, onForgot
    )
}
