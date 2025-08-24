package com.dcharcha.data.di


import com.dcharcha.data.repository.ItemRepositoryImpl
import com.dcharcha.domain.repository.ItemRepository
import com.dcharcha.domain.usecase.GetPagedItemsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindItemRepository(impl: ItemRepositoryImpl): ItemRepository
}


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetPagedItemsUseCase(repo: ItemRepository): GetPagedItemsUseCase =
        GetPagedItemsUseCase(repo)
}
