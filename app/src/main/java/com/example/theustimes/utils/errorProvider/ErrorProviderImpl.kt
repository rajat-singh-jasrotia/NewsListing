package com.example.theustimes.utils.errorProvider

import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.*

class ErrorProviderImpl: ErrorProvider {

    override fun getErrorMessage(error: Throwable?): String {
        var errorJsonString = ""

        try {
            when {
                isNetworkError(error) -> {
                    errorJsonString = "Oops! No Internet Connection"
                }
                error is HttpException -> {
                    errorJsonString = JSONObject(error.response()?.errorBody()?.string()).getString("message")
                }
            }
        } catch (ex: Exception) {
            return when (error) {
                is SocketTimeoutException,
                is UnknownHostException -> {
                   "Oops! No Internet Connection"
                }
                else -> {
                    "Something is not right here"
                }
            }
        }

        return errorJsonString
    }

    /**
     * Is the exception a network error
     * Checks type against a certain set of exception
     */
    override fun isNetworkError(error: Throwable?): Boolean {
        error?.let {
            return when (it) {
                is SocketException,
                is UnknownHostException,
                is SocketTimeoutException,
                is BindException,
                is ConnectException,
                is NoRouteToHostException -> true

                else -> false
            }
        }

        return false
    }
}