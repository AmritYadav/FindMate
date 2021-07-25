package com.amydv.findmate.di

import com.amydv.cache.UserCacheImpl
import com.amydv.data.repository.UserCache
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    abstract fun bindsUserCache(userCacheImpl: UserCacheImpl): UserCache

}