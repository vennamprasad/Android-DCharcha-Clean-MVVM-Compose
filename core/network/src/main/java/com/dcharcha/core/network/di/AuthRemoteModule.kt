package com.dcharcha.core.network.di

import com.dcharcha.core.network.ktor.KtorAuthRemote
import com.dcharcha.core.network.retrofit.AuthApi
import com.dcharcha.core.network.retrofit.RetrofitAuthRemote
import com.dcharcha.domain.repository.AuthRemote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class UseRetrofit
@Qualifier
annotation class UseKtor

@Module
@InstallIn(SingletonComponent::class)
object AuthRemoteModule {
    @Provides
    @Singleton
    fun okHttp(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    @Singleton
    fun retrofit(ok: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.example.com/") // TODO set base URL
        .client(ok)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun api(r: Retrofit): AuthApi = r.create(AuthApi::class.java)

    @Provides
    @Singleton
    @UseRetrofit
    fun retrofitRemote(api: AuthApi): AuthRemote = RetrofitAuthRemote(api)

    @Provides
    @Singleton
    @UseKtor
    fun ktorRemote(ktor: KtorAuthRemote): AuthRemote = ktor

    // Switch active implementation here
    @Provides
    @Singleton
    fun active(@UseRetrofit impl: AuthRemote): AuthRemote = impl
}
