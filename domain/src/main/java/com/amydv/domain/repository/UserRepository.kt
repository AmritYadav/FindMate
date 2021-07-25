package com.amydv.domain.repository

import com.amydv.domain.Result
import com.amydv.domain.models.UserUiModel
import com.amydv.domain.response.User

interface UserRepository {

    suspend fun getRandomUsers(size: Int) : Result<List<UserUiModel>>

    suspend fun updateStatus(userUiModel: UserUiModel)
}