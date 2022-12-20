package com.azimsiddiqui.userlisting.presentation

sealed class ApiResult<T>(
    val data: T? = null,
    val errorMessage: String? = null
) {
    class Success<T>(data: T) : ApiResult<T>(data = data)
    class Error<T>(message: String?, data: T? = null) : ApiResult<T>(errorMessage = message)
    class Loading<T>(data: T? = null) : ApiResult<T>(data = data)
}


enum class Status {
    LOADING,
    ERROR,
    SUCCESS
}