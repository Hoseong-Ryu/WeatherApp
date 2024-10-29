package com.devhoseong.domain.reposiroty

import com.devhoseong.domain.model.City
import com.devhoseong.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeather(lat: Double, lon: Double): Result<Weather>
    suspend fun getCityWeatherInfo(cityName: String): Result<List<City>>
}