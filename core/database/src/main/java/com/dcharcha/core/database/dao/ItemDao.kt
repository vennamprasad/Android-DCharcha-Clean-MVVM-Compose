package com.dcharcha.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dcharcha.core.database.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Query("SELECT * FROM items WHERE title LIKE '%' || :query || '%' ORDER BY id ASC")
    fun paging(query: String): PagingSource<Int, ItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<ItemEntity>)

    @Query("DELETE FROM items")
    suspend fun clearAll()

    @Query("SELECT * FROM items WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): ItemEntity?

    @Query("SELECT * FROM items WHERE id = :id LIMIT 1")
    fun observeById(id: Long): Flow<ItemEntity?>
}
