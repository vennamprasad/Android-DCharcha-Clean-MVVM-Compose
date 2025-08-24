package com.dcharcha.data.repository

import com.dcharcha.core.network.retrofit.AuthApi
import com.dcharcha.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {
    override suspend fun requestOtp(phone: String): Result<Unit> {
        authApi.requestOtp(
            mapOf("phone" to phone)
        )
        return Result.success(
            Unit
        )
    }

    override suspend fun verifyOtp(
        phone: String,
        code: String
    ): Result<String> {
        return try {
            val response = authApi.verifyOtp(
                mapOf(
                    "phone" to phone,
                    "code" to code
                )
            )
            Result.success(response.token)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun loginEmail(
        email: String,
        password: String
    ): Result<String> {
        return try {
            val response = authApi.login(
                mapOf(
                    "email" to email,
                    "password" to password
                )
            )
            Result.success(response.token)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}