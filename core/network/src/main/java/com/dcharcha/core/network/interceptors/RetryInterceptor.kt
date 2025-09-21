package com.dcharcha.core.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.net.SocketTimeoutException

class RetryInterceptor(private val retryAttempts: Int) : Interceptor {
    companion object {
        private const val DEFAULT_RETRY_ATTEMPTS = 3
        fun create(retryAttempts: Int = DEFAULT_RETRY_ATTEMPTS): RetryInterceptor {
            return RetryInterceptor(retryAttempts)
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        for (i in 1..retryAttempts) {
            try {
                return chain.proceed(chain.request())
            } catch (e: SocketTimeoutException) {
                // Log the exception or handle it as needed
                println("SocketTimeoutException on attempt $i: ${e.message}")
            }
        }
        throw RuntimeException("failed to compile the request")
    }
}