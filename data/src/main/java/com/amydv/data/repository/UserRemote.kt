package com.amydv.data.repository

import com.amydv.domain.Result
import com.amydv.domain.response.User

interface UserRemote {

    suspend fun getRandomUsers(size: Int) : Result<List<User>>

}