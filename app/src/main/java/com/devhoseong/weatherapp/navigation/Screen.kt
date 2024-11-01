package com.devhoseong.weatherapp.navigation

import com.devhoseong.domain.model.City
import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data class Main(
        val id: Int? = null,
        val name: String? = null,
        val country: String? = null,
        val lat: Double? = null,
        val lon: Double? = null,
    ) : Screen

    @Serializable
    data object Search : Screen
}