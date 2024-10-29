package com.devhoseong.data.repository

import com.devhoseong.data.local.CityDataSource
import com.devhoseong.data.mapper.mapToCity
import com.devhoseong.data.mapper.mapToWeather
import com.devhoseong.data.remote.api.WeatherApi
import com.devhoseong.domain.model.City
import com.devhoseong.domain.model.Weather
import com.devhoseong.domain.reposiroty.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val cityDataSource: CityDataSource,
) : WeatherRepository {

   override suspend fun getWeather(lat: Double, lon: Double): Result<Weather> = runCatching {
       weatherApi.getWeather(lat = lat, lon = lon).mapToWeather()
   }

   override suspend fun getCityWeatherInfo(cityName: String): Result<List<City>> = runCatching {
       cityDataSource.searchCities(cityName).map { it.mapToCity() }
   }
}