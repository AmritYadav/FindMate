package com.amydv.findmate.di

import android.content.Context
import androidx.room.Room
import com.amydv.cache.rooms.dao.UserDao
import com.amydv.cache.rooms.db.FindMateDatabase
import com.amydv.findmate.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    fun providesRoomDatabase(@ApplicationContext context: Context): FindMateDatabase {
        return Room.databaseBuilder(
            context,
            FindMateDatabase::class.java,
            BuildConfig.FIND_MATE_DB_NAME
        ).build()
    }

    @Provides
    fun providesUserDao(consumerDb: FindMateDatabase): UserDao {
        return consumerDb.userDao()
    }
}