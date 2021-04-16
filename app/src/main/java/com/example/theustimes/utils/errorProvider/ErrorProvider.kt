package com.example.theustimes.utils.errorProvider

import org.json.JSONException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface ErrorProvider {
    fun getErrorMessage(error: Throwable?): String

    fun isNetworkError(error: Throwable?): Boolean
}