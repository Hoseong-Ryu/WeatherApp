package com.devhoseong.domain.usecase

import java.io.IOException

sealed class Failure {
    data object NetworkError : Failure()
    data object UnknownError : Failure()
    data class ApiError(val code: Int, val message: String?) : Failure()
}