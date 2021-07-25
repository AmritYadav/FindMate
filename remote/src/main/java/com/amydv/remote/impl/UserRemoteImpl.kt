package com.amydv.remote.impl

import com.amydv.data.repository.UserRemote
import com.amydv.domain.Result
import com.amydv.domain.response.User
import com.amydv.remote.services.RandomUserServices
import com.amydv.remote.utils.safeApiCall
import java.io.IOException
import javax.inject.Inject

class UserRemoteImpl @Inject constructor(private val randomUserServices: RandomUserServices) : UserRemote {

    override suspend fun getRandomUsers(size: Int): Result<List<User>> =
        safeApiCall(
            call = { requestUser(size) },
            errorMessage = "Error loading users"
        )

    private suspend fun requestUser(size: Int) : Result<List<User>> {
        val response = randomUserServices.requestRandomUsers(size)
        if (response.isSuccessful) {
            response.body()?.let {
                return Result.Success(it.results)
            }
        }
        return Result.Error(IOException("Throw IOException"))
    }

}