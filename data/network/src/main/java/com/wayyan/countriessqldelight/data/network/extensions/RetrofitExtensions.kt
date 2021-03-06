package com.wayyan.countriessqldelight.data.network.extensions

import com.wayyan.countriessqldelight.data.network.exceptions.AuthFailException
import com.wayyan.countriessqldelight.data.network.exceptions.NetworkException
import com.wayyan.countriessqldelight.data.network.exceptions.NoContentException
import retrofit2.Call
import retrofit2.Response

private const val NO_CONTENT_ERROR_CODE = 204
private const val AUTH_FAIL_CODE = 433

internal fun <T> Call<T>.executeOrThrow(): T {
    val response = this.execute()
    return response.getBodyOrThrowNetworkException()
}

internal fun <T> Response<T>.getBodyOrThrowNetworkException(): T {

    if (this.isSuccessful.not()) {
        val errorString = this.errorBody()!!
            .byteStream()
            .bufferedReader()
            .use { it.readText() }
        if (this.code() == AUTH_FAIL_CODE) {
            throw AuthFailException()
        }
        throw NetworkException(
            errorString, this.code()
        )
    }

    if (this.code() == NO_CONTENT_ERROR_CODE) {
        throw NoContentException()
    } else if (this.code() == AUTH_FAIL_CODE) {
        throw AuthFailException()
    }

    val body = this.body() ?: throw NetworkException(errorCode = this.code())
    return body
}