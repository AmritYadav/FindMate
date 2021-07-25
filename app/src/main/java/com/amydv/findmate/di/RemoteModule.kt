package com.amydv.findmate.di

import com.amydv.data.repository.UserRemote
import com.amydv.remote.impl.UserRemoteImpl
import com.amydv.remote.services.RandomUserServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {

    companion object {
        @Provides
        fun providesConsumerService(retrofit: Retrofit): RandomUserServices {
            return retrofit.create(RandomUserServices::class.java)
        }
    }

    @Binds
    abstract fun bindsUserRemote(userRemoteImpl: UserRemoteImpl): UserRemote
}