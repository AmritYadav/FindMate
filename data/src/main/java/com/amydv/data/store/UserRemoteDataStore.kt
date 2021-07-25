package com.amydv.data.store

import com.amydv.data.repository.UserDataStore
import com.amydv.data.repository.UserRemote
import com.amydv.domain.Result
import com.amydv.domain.models.UserUiModel
import com.amydv.domain.response.User
import javax.inject.Inject

class UserRemoteDataStore @Inject constructor(private val userRemote: UserRemote) : UserDataStore {

    override suspend fun getRandomUsers(size: Int): Result<List<User>> {
        return userRemote.getRandomUsers(size)
    }

    override suspend fun saveUsers(users: List<User>) {
        throw IllegalAccessException("Invalid call to Save Users List from Remote")
    }

    override suspend fun getAllUsers(): List<UserUiModel> {
        throw IllegalAccessException("Invalid call to Get Users List from Remote")
    }

    override suspend fun updateStatus(userUiModel: UserUiModel) {
        throw IllegalAccessException("Invalid call to Update User Status from Remote")
    }

}