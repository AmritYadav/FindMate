package com.amydv.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amydv.domain.CoroutinesDispatcherProvider
import com.amydv.domain.Result
import com.amydv.domain.models.UserUiModel
import com.amydv.domain.usecase.GetRandomUserUseCase
import com.amydv.presentation.states.Resource
import com.amydv.presentation.states.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GetRandomUserViewModel @Inject constructor(
    private val getRandomUserUseCase: GetRandomUserUseCase,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    private var _users = MutableLiveData<Resource<List<UserUiModel>>>()
    val users: LiveData<Resource<List<UserUiModel>>> = _users

    fun loadRandomUser(size: Int) = viewModelScope.launch {
        _users.value = Resource(ResourceState.LOADING)

        val result = withContext(coroutinesDispatcherProvider.io) {
            return@withContext getRandomUserUseCase.getRandomUser(size)
        }

        when (result) {
            is Result.Success -> {
                _users.value = Resource(ResourceState.SUCCESS, data = result.data)
            }
            is Result.Error -> {
                _users.value = Resource(ResourceState.ERROR, errMsg = result.exception.message)
            }
        }
    }
}