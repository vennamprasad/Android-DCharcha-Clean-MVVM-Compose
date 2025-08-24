package com.dcharcha.core.database.di

import android.content.Context
import androidx.room.Room
import com.dcharcha.core.database.AppDatabase
import com.dcharcha.core.database.dao.RemoteKeyDao
import com.dcharcha.core.database.dao.UserProfileDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun db(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "DCharcha.db").build()

    @Provides
    fun profileDao(db: AppDatabase): UserProfileDao = db.userProfileDao()

    @Provides
    fun itemDao(db: AppDatabase) = db.itemDao()

    @Provides
    fun remoteKeyDao(db: AppDatabase): RemoteKeyDao = db.remoteKeyDao()
}
