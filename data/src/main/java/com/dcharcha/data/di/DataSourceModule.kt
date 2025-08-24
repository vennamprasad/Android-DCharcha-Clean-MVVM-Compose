package com.dcharcha.data.di

import com.dcharcha.data.local.ItemLocalDataSource
import com.dcharcha.data.local.ItemLocalDataSourceImpl
import com.dcharcha.data.remote.ItemRemoteDataSource
import com.dcharcha.data.remote.ItemRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindItemLocal(impl: ItemLocalDataSourceImpl): ItemLocalDataSource

    @Binds
    @Singleton
    abstract fun bindItemRemote(impl: ItemRemoteDataSourceImpl): ItemRemoteDataSource
}