package com.dcharcha.core.network.ktor

import com.dcharcha.domain.repository.AuthRemote
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.serialization.Serializable
import javax.inject.Inject

@Serializable
data class TokenDto(val token: String)

class KtorAuthRemote @Inject constructor(private val client: HttpClient) : AuthRemote {
    override suspend fun requestOtp(phone: String) = runCatching {
        client.post("auth/request-otp") { setBody(mapOf("phone" to phone)) }; Unit
    }

    override suspend fun verifyOtp(phone: String, code: String) = runCatching {
        client.post("auth/verify-otp") { setBody(mapOf("phone" to phone, "code" to code)) }
            .body<TokenDto>().token
    }

    override suspend fun loginEmail(email: String, password: String) = runCatching {
        client.post("auth/login") { setBody(mapOf("email" to email, "password" to password)) }
            .body<TokenDto>().token
    }
}
