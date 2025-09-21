package com.dcharcha.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dcharcha.core.datastore.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
) : ViewModel() {

    fun setAuthTokens(token: String, refreshToken: String) {
        viewModelScope.launch {
            userPreferencesRepository.setAuthTokens(token, refreshToken)
        }
    }

    val isLoggedIn: Flow<Boolean> = userPreferencesRepository.isLoggedIn
}