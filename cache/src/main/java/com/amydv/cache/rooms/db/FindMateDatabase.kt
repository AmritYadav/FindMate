package com.amydv.cache.rooms.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amydv.cache.rooms.dao.UserDao
import com.amydv.cache.rooms.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class FindMateDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}