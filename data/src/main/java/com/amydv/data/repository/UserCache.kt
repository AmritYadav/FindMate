package com.amydv.data.repository

import com.amydv.domain.models.UserUiModel
import com.amydv.domain.response.User

interface UserCache {

    suspend fun saveUsers(users: List<User>)

    suspend fun getUsers(): List<UserUiModel>

    suspend fun updateStatus(userUiModel: UserUiModel)

    suspend fun cleaDb()
}