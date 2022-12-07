package com.production.myapp.core

sealed class ResultNetwork<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : ResultNetwork<T>(data)
    class Error<T>(message: String, data: T? = null) : ResultNetwork<T>(data, message)
    class Loading<T>(data: T? = null) : ResultNetwork<T>(data)
}