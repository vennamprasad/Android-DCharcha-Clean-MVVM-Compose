package com.dcharcha.core.network.retrofit

import com.dcharcha.core.network.dto.TokenDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/request-otp")
    suspend fun requestOtp(@Body body: Map<String, String>)
    @POST("auth/verify-otp")
    suspend fun verifyOtp(@Body body: Map<String, String>): TokenDto
    @POST("auth/login")
    suspend fun login(@Body body: Map<String, String>): TokenDto
}
