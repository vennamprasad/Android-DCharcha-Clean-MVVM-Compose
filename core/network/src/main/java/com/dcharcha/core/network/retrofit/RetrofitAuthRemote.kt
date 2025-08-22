package com.dcharcha.core.network.retrofit

import com.dcharcha.domain.repository.AuthRemote
import javax.inject.Inject

class RetrofitAuthRemote @Inject constructor(
    private val api: AuthApi
) : AuthRemote {
    override suspend fun requestOtp(phone: String) = runCatching {
        api.requestOtp(mapOf("phone" to phone)); Unit
    }

    override suspend fun verifyOtp(phone: String, code: String) = runCatching {
        api.verifyOtp(mapOf("phone" to phone, "code" to code)).token
    }

    override suspend fun loginEmail(email: String, password: String) = runCatching {
        api.login(mapOf("email" to email, "password" to password)).token
    }
}
