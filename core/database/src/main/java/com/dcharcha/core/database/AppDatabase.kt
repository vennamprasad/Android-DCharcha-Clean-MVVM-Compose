package com.dcharcha.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dcharcha.core.database.dao.ItemDao
import com.dcharcha.core.database.dao.RemoteKeyDao
import com.dcharcha.core.database.dao.UserProfileDao
import com.dcharcha.core.database.entity.ItemEntity
import com.dcharcha.core.database.entity.RemoteKey
import com.dcharcha.core.database.entity.UserProfile

@Database(
    entities = [
        ItemEntity::class,
        RemoteKey::class,
        UserProfile::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao
    abstract fun itemDao(): ItemDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}
