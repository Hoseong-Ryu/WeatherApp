package com.devhoseong.weatherapp

import com.devhoseong.domain.usecase.Failure
import retrofit2.HttpException
import java.io.IOException

fun Throwable.mapToFailure(): Failure {
    return when (this) {
        is IOException -> Failure.NetworkError
        is HttpException -> Failure.ApiError(code(), message())
        else -> Failure.UnknownError
    }
}