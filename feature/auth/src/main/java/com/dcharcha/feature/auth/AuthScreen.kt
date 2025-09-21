package com.dcharcha.feature.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dcharcha.feature.auth.components.EmailLogin
import com.dcharcha.feature.auth.components.MobileLogin
import com.dcharcha.feature.auth.components.SegmentedButtons

@Composable
fun AuthScreen(
    viewModel: AuthViewModel,
    onMobileOtp: (String) -> Unit,
    onEmailLogin: () -> Unit,
    onSignup: () -> Unit,
    onForgot: () -> Unit,
) {
    var tab by remember { mutableIntStateOf(0) }
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SegmentedButtons(tab) { tab = it }
        if (tab == 0) MobileLogin(onMobileOtp,viewModel) else EmailLogin(
            onEmailLogin, onSignup, onForgot,viewModel
        )
    }
}