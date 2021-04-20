package com.example.theustimes.errorProvider

interface ErrorProvider {
    fun getErrorMessage(error: Throwable?): String

    fun isNetworkError(error: Throwable?): Boolean
}