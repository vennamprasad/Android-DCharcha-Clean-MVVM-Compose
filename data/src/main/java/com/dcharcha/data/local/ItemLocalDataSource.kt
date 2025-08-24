package com.dcharcha.data.local

import androidx.paging.PagingSource
import com.dcharcha.core.database.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

interface ItemLocalDataSource {
    fun pagingSource(query: String): PagingSource<Int, ItemEntity>
    fun observeById(id: Long): Flow<ItemEntity?>
    suspend fun upsertAll(items: List<ItemEntity>)
    suspend fun clearAll()
    suspend fun getById(id: Long): ItemEntity?
}