package com.amydv.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amydv.domain.CoroutinesDispatcherProvider
import com.amydv.domain.models.UserUiModel
import com.amydv.domain.usecase.UpdateStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UpdateUserStatusViewModel @Inject constructor(
    private val updateStatusUseCase: UpdateStatusUseCase,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    fun update(userUiModel: UserUiModel) = viewModelScope.launch {
        withContext(coroutinesDispatcherProvider.io) {
            updateStatusUseCase.updateStatus(userUiModel)
        }
    }
}