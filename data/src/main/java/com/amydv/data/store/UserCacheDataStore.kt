package com.amydv.data.store

import com.amydv.data.repository.UserCache
import com.amydv.data.repository.UserDataStore
import com.amydv.domain.Result
import com.amydv.domain.models.UserUiModel
import com.amydv.domain.response.User
import java.lang.UnsupportedOperationException
import javax.inject.Inject

class UserCacheDataStore @Inject constructor(private val userCache: UserCache) : UserDataStore {

    override suspend fun getRandomUsers(size: Int): Result<List<User>> {
        throw UnsupportedOperationException("Invalid call to Random Users List from Cache")
    }

    override suspend fun saveUsers(users: List<User>) {
        userCache.saveUsers(users)
    }

    override suspend fun getAllUsers() : List<UserUiModel> {
        return userCache.getUsers()
    }

    override suspend fun updateStatus(userUiModel: UserUiModel) {
        userCache.updateStatus(userUiModel)
    }
}