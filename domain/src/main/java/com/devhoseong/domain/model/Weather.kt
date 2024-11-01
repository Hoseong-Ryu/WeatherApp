package com.devhoseong.domain.model

data class Weather(
    val currentWeather: CurrentWeather,
    val hourlyForecasts: List<HourlyForecast>,
    val dailyForecasts: List<DailyForecast>
)

data class CurrentWeather(
    val temperature: Int,
    val minTemp: Int,
    val maxTemp: Int,
    val humidity: Int,
    val windSpeed: Double,
    val clouds: Int,
    val weatherIcon: String,
    val weatherDescription: String
)

data class HourlyForecast(
    val timestamp: Long,
    val temperature: Int,
    val weatherIcon: String
)

data class DailyForecast(
    val timestamp: Long,
    val minTemp: Int,
    val maxTemp: Int,
    val weatherIcon: String
)

data class City(
    val id: Int,
    val name: String,
    val country: String,
    val lat: Double,
    val lon: Double
)