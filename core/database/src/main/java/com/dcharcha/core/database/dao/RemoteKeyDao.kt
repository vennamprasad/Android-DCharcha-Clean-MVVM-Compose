package com.dcharcha.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dcharcha.core.database.entity.RemoteKey

@Dao
interface RemoteKeyDao {
    @Query("SELECT * FROM remote_keys WHERE id = :keyId LIMIT 1")
    suspend fun get(keyId: String): RemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(key: RemoteKey)

    @Query("DELETE FROM remote_keys WHERE id = :keyId")
    suspend fun clear(keyId: String)
}
