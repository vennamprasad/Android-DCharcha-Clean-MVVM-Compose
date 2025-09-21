package com.dcharcha.core.network

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.annotation.RequiresPermission
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class Utils {
    companion object {
        @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val network = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

            return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
        }

        // Use TLS instead of deprecated SSL
        private fun getSSLContext(): SSLContext {
            return SSLContext.getInstance("TLS").apply {
                init(null, trustAllCerts, SecureRandom())
            }
        }

        fun getSSLSocketFactory(): SSLSocketFactory {
            return getSSLContext().socketFactory
        }

        val trustAllCerts: Array<TrustManager> = arrayOf(
            @SuppressLint("CustomX509TrustManager")
            object : X509TrustManager {
                override fun checkClientTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?,
                ) = Unit

                override fun checkServerTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?,
                ) = Unit

                override fun getAcceptedIssuers(): Array<X509Certificate> = emptyArray()
            }
        )
    }

    fun convertErrorBodyToJson(errorBody: ResponseBody?): String? {
        return try {
            val jsonString = errorBody?.string()
            val gson = Gson()
            gson.fromJson(jsonString, JsonObject::class.java).get("message").asString
        } catch (e: Exception) {
            null
        }
    }
}
