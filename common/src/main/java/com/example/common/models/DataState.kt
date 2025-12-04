package com.example.common.models

sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()

    data class Error(val exception: Exception) : DataState<Nothing>() {
        val errorMessage = "${exception.message}"
    }

    object Loading : DataState<Nothing>()

    fun <T, R> DataState<T>.mapData(mapper: (T) -> R): DataState<R> {
        return when (this) {
            is Success -> Success(mapper(data))
            is Error -> this
            is Loading -> this
        }
    }
}
