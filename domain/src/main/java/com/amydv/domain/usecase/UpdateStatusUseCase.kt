package com.amydv.domain.usecase

import com.amydv.domain.models.UserUiModel
import com.amydv.domain.repository.UserRepository
import javax.inject.Inject

class UpdateStatusUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun updateStatus(userUiModel: UserUiModel) {
        return userRepository.updateStatus(userUiModel)
    }

}