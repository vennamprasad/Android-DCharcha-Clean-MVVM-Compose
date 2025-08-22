package com.dcharcha.domain.repository

interface AuthRemote {
    suspend fun requestOtp(phone: String): Result<Unit>
    suspend fun verifyOtp(phone: String, code: String): Result<String>
    suspend fun loginEmail(email: String, password: String): Result<String>
}
