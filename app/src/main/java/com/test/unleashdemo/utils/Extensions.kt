package com.test.unleashdemo.utils

import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T, R> UnleashResponse<T>.responseMap(map: suspend (T) -> R): UnleashResponse<R> {
    return when (this) {
        is UnleashResponse.Success -> UnleashResponse.Success(map(this.data))
        is UnleashResponse.Failure -> UnleashResponse.Failure(error)
    }
}

suspend fun <T>getUnleashResponse(data: suspend () -> T): UnleashResponse<T> {
    return try {
        UnleashResponse.Success(data())
    } catch (e: Exception) {
        val error = when (e) {
            is UnknownHostException, is SocketTimeoutException -> "No Internet Connection"
            is HttpException -> e.message.toString()
            else -> "Generic Error"
        }
        UnleashResponse.Failure(error)
    }
}