package com.devhoseong.weatherapp

import com.devhoseong.domain.model.City
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

fun City.isValid(): Boolean {
    return this.run {
        country.isNotBlank() &&
                name.isNotBlank() &&
                lat != 0.0 &&
                lon != 0.0 &&
                id != 0

    }

}