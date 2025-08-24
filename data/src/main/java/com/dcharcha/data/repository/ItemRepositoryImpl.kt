package com.dcharcha.data.repository


import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.dcharcha.core.database.AppDatabase
import com.dcharcha.data.local.ItemLocalDataSource
import com.dcharcha.data.mapper.toDomain
import com.dcharcha.data.remote.ItemRemoteDataSource
import com.dcharcha.data.remote.ItemRemoteMediator
import com.dcharcha.domain.repository.ItemRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val db: AppDatabase,
    private val local: ItemLocalDataSource,
    private val remote: ItemRemoteDataSource,
) : ItemRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun pagedItems(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2,
                enablePlaceholders = false,
                initialLoadSize = 20 * 2
            ),
            remoteMediator = ItemRemoteMediator(db, remote, query, 20),
            pagingSourceFactory = { local.pagingSource(query) }
        ).flow.map { it.map { entity -> entity.toDomain() } }
}

