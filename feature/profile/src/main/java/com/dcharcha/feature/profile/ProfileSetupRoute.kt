package com.dcharcha.feature.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dcharcha.core.database.entity.UserProfile
import com.dcharcha.core.database.dao.UserProfileDao
import com.dcharcha.core.ui.DynamicBackdrop
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSetupRoute(
    uid: String = "local",
    onDone: () -> Unit,
    vm: ProfileViewModel = hiltViewModel()
) {
    var first by remember { mutableStateOf("") }
    var last by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var photo by remember { mutableStateOf<String?>(null) }

    DynamicBackdrop {
        Scaffold(topBar = { TopAppBar(title = { Text("Profile setup") }) }) { padding ->
            Column(Modifier
                .padding(padding)
                .padding(16.dp)) {
                OutlinedTextField(
                    first,
                    { first = it },
                    label = { Text("First name") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    last,
                    { last = it },
                    label = { Text("Last name") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    email,
                    { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    address,
                    { address = it },
                    label = { Text("Address") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                Button(onClick = {
                    vm.save(UserProfile(uid, first, last, email, address, photo)); onDone()
                }) { Text("Save & Continue") }
            }
        }
    }
}

@HiltViewModel
class ProfileViewModel @Inject constructor(private val dao: UserProfileDao) : ViewModel() {
    fun save(p: UserProfile) = viewModelScope.launch { dao.upsert(p) }
}
