package com.amydv.presentation.states

class Resource<T>(val status: ResourceState, val data: T? = null, val errMsg: String? = null)
