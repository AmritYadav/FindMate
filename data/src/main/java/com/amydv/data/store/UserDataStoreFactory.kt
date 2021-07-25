package com.amydv.data.store

import com.amydv.data.repository.UserDataStore
import javax.inject.Inject

class UserDataStoreFactory @Inject constructor(
    private val userCacheDataStore: UserCacheDataStore,
    private val userRemoteDataStore: UserRemoteDataStore
) {

    fun getCacheDataStore(): UserDataStore = userCacheDataStore

    fun getRemoteDataStore(): UserDataStore = userRemoteDataStore
}