package com.dcharcha.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.dcharcha.core.database.AppDatabase
import com.dcharcha.core.database.entity.ItemEntity
import com.dcharcha.core.database.entity.RemoteKey
import com.dcharcha.core.network.retrofit.ItemApi
import com.dcharcha.data.mapper.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPagingApi::class)
class ItemRemoteMediator(
    private val db: AppDatabase,
    private val api: ItemApi,
    private val query: String,
    private val pageSize: Int = 20
) : RemoteMediator<Int, ItemEntity>() {

    private val keyId get() = "items:$query"

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ItemEntity>
    ): MediatorResult = withContext(Dispatchers.IO) {
        try {
            val keyDao = db.remoteKeyDao()
            val itemDao = db.itemDao()

            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> {
                    val key = keyDao.get(keyId)
                    val prev = key?.prevKey
                    if (prev == null) return@withContext MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                    prev
                }

                LoadType.APPEND -> {
                    val key = keyDao.get(keyId)
                    val next = key?.nextKey ?: 1
                    next
                }
            }

            val network =
                api.getItems(page, state.config.pageSize.takeIf { it > 0 } ?: pageSize)

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    itemDao.clearAll()
                    keyDao.clear(keyId)
                }
                itemDao.upsertAll(network.map { it.toEntity() })

                val endReached = network.isEmpty()
                val nextKey = if (endReached) null else page + 1
                val prevKey = if (page == 1) null else page - 1
                keyDao.upsert(RemoteKey(id = keyId, nextKey = nextKey, prevKey = prevKey))
            }

            MediatorResult.Success(endOfPaginationReached = network.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
