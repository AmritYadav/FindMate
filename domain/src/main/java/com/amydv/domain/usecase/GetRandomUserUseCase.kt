package com.amydv.domain.usecase

import com.amydv.domain.Result
import com.amydv.domain.models.UserUiModel
import com.amydv.domain.response.User
import com.amydv.domain.repository.UserRepository
import javax.inject.Inject

class GetRandomUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun getRandomUser(size: Int) : Result<List<UserUiModel>> {
        return userRepository.getRandomUsers(size)
    }

}