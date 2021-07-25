package com.amydv.remote.services

import com.amydv.domain.response.RandomUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserServices {

    @GET("api/")
    suspend fun requestRandomUsers(
        @Query("results") results: Int
    ): Response<RandomUserResponse>

}