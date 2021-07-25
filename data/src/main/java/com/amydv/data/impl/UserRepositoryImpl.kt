package com.amydv.data.impl

import com.amydv.data.store.UserDataStoreFactory
import com.amydv.domain.Result
import com.amydv.domain.models.UserUiModel
import com.amydv.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataStoreFactory: UserDataStoreFactory
) : UserRepository {

    override suspend fun getRandomUsers(size: Int): Result<List<UserUiModel>> {
        return when (val result = userDataStoreFactory.getRemoteDataStore().getRandomUsers(size)) {
            is Result.Success -> {
                val users = result.data
                userDataStoreFactory.getCacheDataStore().saveUsers(users)
                val cachedList = userDataStoreFactory.getCacheDataStore().getAllUsers()
                Result.Success(data = cachedList)
            }
            is Result.Error -> {
                Result.Error(result.exception)
            }
        }
    }

    override suspend fun updateStatus(userUiModel: UserUiModel) {
        userDataStoreFactory.getCacheDataStore().updateStatus(userUiModel)
    }
}