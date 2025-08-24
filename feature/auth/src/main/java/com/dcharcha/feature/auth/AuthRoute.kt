package com.dcharcha.feature.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.dcharcha.core.ui.DynamicBackdrop

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthRoute(
    onMobileOtp: (String) -> Unit,
    onEmailLogin: () -> Unit,
    onSignup: () -> Unit,
    onForgot: () -> Unit
) {
    var tab by remember { mutableIntStateOf(0) }
    DynamicBackdrop {
        Scaffold(topBar = { CenterAlignedTopAppBar(title = { Text("Sign in") }) }) { padding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                SegmentedButtons(tab) { tab = it }
                if (tab == 0) MobileLogin(onMobileOtp) else EmailLogin(
                    onEmailLogin,
                    onSignup,
                    onForgot
                )
            }
        }
    }
}

@Composable
private fun SegmentedButtons(selected: Int, onChange: (Int) -> Unit) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        FilterChip(selected == 0, { onChange(0) }, label = { Text("Mobile") })
        FilterChip(selected == 1, { onChange(1) }, label = { Text("Email") })
    }
}

@Composable
private fun MobileLogin(onOTPRequested: (String) -> Unit) {
    var phone by remember { mutableStateOf("") }
    OutlinedTextField(
        value = phone,
        onValueChange = { phone = it },
        label = { Text("Mobile number") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(Modifier.height(12.dp))
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { if (phone.length >= 10) onOTPRequested(phone) }) { Text("Get OTP") }
}

@Composable
private fun EmailLogin(
    onSuccess: () -> Unit,
    onSignupClick: () -> Unit,
    onForgotClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    OutlinedTextField(
        email,
        { email = it },
        label = { Text("Email") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        password,
        { password = it },
        label = { Text("Password") },
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(Modifier.height(12.dp))
    Button(onClick = onSuccess) { Text("Sign in") }
    TextButton(onClick = onSignupClick) { Text("Create account") }
    TextButton(onClick = onForgotClick) { Text("Forgot password?") }
}
