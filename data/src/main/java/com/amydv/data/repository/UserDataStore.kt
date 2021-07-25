package com.amydv.data.repository

import com.amydv.domain.Result
import com.amydv.domain.models.UserUiModel
import com.amydv.domain.response.User

interface UserDataStore {

    suspend fun getRandomUsers(size: Int) : Result<List<User>>

    suspend fun saveUsers(users: List<User>)

    suspend fun getAllUsers() : List<UserUiModel>

    suspend fun updateStatus(userUiModel: UserUiModel)
}