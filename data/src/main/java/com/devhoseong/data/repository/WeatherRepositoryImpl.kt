package com.devhoseong.data.repository

import com.devhoseong.data.mapper.mapToWeather
import com.devhoseong.data.remote.api.WeatherApi
import com.devhoseong.domain.model.Weather
import com.devhoseong.domain.reposiroty.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRepository {

   override suspend fun getWeather(lat: Double, lon: Double): Result<Weather> = runCatching {
       weatherApi.getWeather(lat = lat, lon = lon).mapToWeather()
   }
}