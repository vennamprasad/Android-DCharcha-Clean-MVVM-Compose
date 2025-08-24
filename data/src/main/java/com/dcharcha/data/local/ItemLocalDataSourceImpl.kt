package com.dcharcha.data.local

import androidx.paging.PagingSource
import com.dcharcha.core.database.AppDatabase
import com.dcharcha.core.database.entity.ItemEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemLocalDataSourceImpl @Inject constructor(
    private val db: AppDatabase
) : ItemLocalDataSource {
    private val dao get() = db.itemDao()

    override fun pagingSource(query: String): PagingSource<Int, ItemEntity> =
        dao.paging(query)

    override fun observeById(id: Long): Flow<ItemEntity?> =
        dao.observeById(id)

    override suspend fun upsertAll(items: List<ItemEntity>) =
        dao.upsertAll(items)

    override suspend fun clearAll() =
        dao.clearAll()

    override suspend fun getById(id: Long): ItemEntity? =
        dao.getById(id)
}