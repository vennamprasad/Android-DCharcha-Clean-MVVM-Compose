package com.dcharcha.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.dcharcha.core.database.AppDatabase
import com.dcharcha.core.database.entity.ItemEntity
import com.dcharcha.core.database.entity.RemoteKey
import com.dcharcha.data.mapper.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPagingApi::class)
class ItemRemoteMediator(
    private val db: AppDatabase,
    private val remote: ItemRemoteDataSource,
    private val query: String,
    private val pageSize: Int
) : RemoteMediator<Int, ItemEntity>() {

    private val keyId get() = "items:$query"

    override suspend fun load(loadType: LoadType, state: PagingState<Int, ItemEntity>) =
        withContext(Dispatchers.IO) {
            try {
                val keyDao = db.remoteKeyDao()
                val itemDao = db.itemDao()

                val page = when (loadType) {
                    LoadType.REFRESH -> 1
                    LoadType.PREPEND -> {
                        val prev = keyDao.get(keyId)?.prevKey
                        if (prev == null) return@withContext MediatorResult.Success(true)
                        prev
                    }
                    LoadType.APPEND -> {
                        val next = keyDao.get(keyId)?.nextKey
                        if (next == null) return@withContext MediatorResult.Success(true)
                        next
                    }
                }

                val requested = state.config.pageSize.takeIf { it > 0 } ?: pageSize
                val network = remote.getItems(page, requested)

                db.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        itemDao.clearAll()
                        keyDao.clear(keyId)
                    }
                    itemDao.upsertAll(network.map { it.toEntity() })

                    val end = network.size < requested
                    val nextKey = if (end) null else page + 1
                    val prevKey = if (page == 1) null else page - 1
                    keyDao.upsert(RemoteKey(id = keyId, nextKey = nextKey, prevKey = prevKey))
                }

                MediatorResult.Success(endOfPaginationReached = network.isEmpty())
            } catch (e: Exception) {
                MediatorResult.Error(e)
            }
        }
}