package com.dcharcha.core.network

import retrofit2.Response

sealed class NetworkState<out T> {
    data class Success<out T>(val data: T) : NetworkState<T>()
    data class Error<T>(val response: Response<T>) : NetworkState<T>()
    data class Exception(val message: String) : NetworkState<Nothing>()
}