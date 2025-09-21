package com.dcharcha.feature.auth.components


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dcharcha.feature.auth.AuthViewModel

@Composable
fun EmailLogin(
    onSuccess: () -> Unit,
    onSignupClick: () -> Unit,
    onForgotClick: () -> Unit,
    viewModel: AuthViewModel,
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