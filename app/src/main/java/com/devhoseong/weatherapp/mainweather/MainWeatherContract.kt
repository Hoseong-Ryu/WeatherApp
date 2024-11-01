package com.devhoseong.weatherapp.mainweather

import com.devhoseong.domain.model.City
import com.devhoseong.domain.model.Weather
import com.devhoseong.domain.usecase.Failure


/**
 * UI State that represents MainWeatherScreen
 **/
sealed interface WeatherState {
    data object Loading : WeatherState
    data class Success(
        val weather: Weather? = null,
        val cities: List<City> = emptyList()
    ) : WeatherState
    data class Error(val failure: Failure) : WeatherState
}

/**
 * MainWeather Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class MainWeatherActions(
    val OnClick: () -> Unit = {},
)


