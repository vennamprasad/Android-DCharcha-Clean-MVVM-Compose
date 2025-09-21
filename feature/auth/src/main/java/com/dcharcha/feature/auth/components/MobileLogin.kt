package com.dcharcha.feature.auth.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.dcharcha.feature.auth.AuthViewModel

@Composable
fun MobileLogin(onOTPRequested: (String) -> Unit, viewModel: AuthViewModel) {
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