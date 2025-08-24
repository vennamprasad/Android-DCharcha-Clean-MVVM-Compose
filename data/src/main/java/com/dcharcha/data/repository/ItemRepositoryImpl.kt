package com.dcharcha.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.dcharcha.core.database.AppDatabase
import com.dcharcha.core.network.retrofit.ItemApi
import com.dcharcha.data.mapper.toDomain
import com.dcharcha.data.remote.ItemRemoteMediator
import com.dcharcha.domain.repository.ItemRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val db: AppDatabase,
    private val api: ItemApi,
    private val pageSize: Int = 20,
    private val prefetchDistance: Int = 2,
    private val enablePlaceholders: Boolean = false
) : ItemRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun pagedItems(query: String) =
        Pager(
            config = PagingConfig(pageSize, prefetchDistance, enablePlaceholders),
            remoteMediator = ItemRemoteMediator(db, api, query),
            pagingSourceFactory = { db.itemDao().paging(query) }
        ).flow.map { pagingData ->
            pagingData.map {
                it.toDomain()
            }
        }
}
