package com.test.unleashdemo.utils

sealed class UnleashResponse<T> {
    data class Success<T>(val data: T): UnleashResponse<T>()
    data class Failure<T>(val error: String): UnleashResponse<T>()
}