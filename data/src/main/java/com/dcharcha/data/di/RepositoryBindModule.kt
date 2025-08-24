package com.dcharcha.data.di

import com.dcharcha.core.database.AppDatabase
import com.dcharcha.core.network.retrofit.ItemApi
import com.dcharcha.data.repository.ItemRepositoryImpl
import com.dcharcha.domain.repository.ItemRepository
import com.dcharcha.domain.usecase.GetPagedItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryProvideModule {
    @Provides
    @Singleton
    fun provideItemRepository(
        db: AppDatabase,
        api: ItemApi
    ): ItemRepository = ItemRepositoryImpl(db, api)
}


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetPagedItemsUseCase(repo: ItemRepository): GetPagedItemsUseCase =
        GetPagedItemsUseCase(repo)
}
