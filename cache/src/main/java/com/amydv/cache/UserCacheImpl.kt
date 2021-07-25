package com.amydv.cache

import com.amydv.cache.rooms.entity.toUserEntity
import com.amydv.cache.rooms.entity.toUserUiModel
import com.amydv.cache.rooms.utils.getStatus
import com.amydv.data.repository.UserCache
import com.amydv.domain.models.UserUiModel
import com.amydv.domain.response.User
import javax.inject.Inject

class UserCacheImpl @Inject constructor(private val userLocalDataSource: UserLocalDataSource) : UserCache {

    override suspend fun saveUsers(users: List<User>) {
        userLocalDataSource.saveUsers(users.map { it.toUserEntity() })
    }

    override suspend fun getUsers(): List<UserUiModel> {
        return userLocalDataSource.getUsers().map { it.toUserUiModel() }
    }

    override suspend fun updateStatus(userUiModel: UserUiModel) {
        with (userUiModel) {
            userLocalDataSource.updateStatus(getStatus(status), dob, email)
        }
    }

    override suspend fun cleaDb() {
        userLocalDataSource.clearDb()
    }
}