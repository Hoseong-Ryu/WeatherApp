package com.devhoseong.domain.reposiroty

import com.devhoseong.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeather(lat: Double, lon: Double): Result<Weather>
}