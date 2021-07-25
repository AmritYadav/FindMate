package com.amydv.cache

import com.amydv.cache.rooms.dao.UserDao
import com.amydv.cache.rooms.entity.UserEntity
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(private val userDao: UserDao) {

    suspend fun getUsers(): List<UserEntity> = userDao.getUsers()

    suspend fun saveUsers(userEntities: List<UserEntity>) = userDao.saveUsers(userEntities)

    suspend fun updateStatus(status: Int, dob: String, email: String) =
        userDao.updateUserStatus(status, dob, email)

    suspend fun clearDb() = userDao.clearUser()
}