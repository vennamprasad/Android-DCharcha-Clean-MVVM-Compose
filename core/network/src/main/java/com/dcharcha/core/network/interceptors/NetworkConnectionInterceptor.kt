package com.dcharcha.core.network.interceptors

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import com.dcharcha.core.network.Utils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class NetworkConnectionInterceptor @Inject constructor(private val mContext: Context) : Interceptor {
    companion object {
        private const val NO_INTERNET_CONNECTION = "No Internet Connection"
    }
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!Utils.isNetworkAvailable(mContext)) {
            throw IOException(NO_INTERNET_CONNECTION)
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}