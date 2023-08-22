package com.test.unsplashcompose.utils

import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T, R> Response<T>.responseMap(map: suspend (T) -> R): Response<R> {
    return when (this) {
        is Response.Success -> Response.Success(map(this.data))
        is Response.Failure -> Response.Failure(error)
    }
}

suspend fun <T>getResponse(data: suspend () -> T): Response<T> {
    return try {
        Response.Success(data())
    } catch (e: Exception) {
        val error = when (e) {
            is UnknownHostException, is SocketTimeoutException -> "No Internet Connection"
            is HttpException -> e.message.toString()
            else -> "Generic Error"
        }
        Response.Failure(error)
    }
}