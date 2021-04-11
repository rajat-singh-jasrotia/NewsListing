package com.example.theustimes.utils

sealed class Result<out T> {
    object Loading : Result<Nothing>()
    class Error(val errorMessage: String? = null) : Result<Nothing>()
    class Success<out T>(val data: T) : Result<T>()
}