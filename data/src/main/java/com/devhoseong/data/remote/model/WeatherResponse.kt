package com.devhoseong.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
   val lat: Double,
   val lon: Double,
   val timezone: String,
   val current: CurrentDTO,
   val hourly: List<HourlyDTO>,
   val daily: List<DailyDTO>
)

@Serializable
data class CurrentDTO(
   val dt: Long,
   val temp: Double,
   val humidity: Int,
   val clouds: Int,
   @SerialName("wind_speed") val windSpeed: Double,
   val weather: List<WeatherDTO>
)

@Serializable
data class HourlyDTO(
   val dt: Long,
   val temp: Double,
   val weather: List<WeatherDTO>
)

@Serializable
data class DailyDTO(
   val dt: Long,
   @SerialName("temp") 
   val temperature: TemperatureDTO,
   val weather: List<WeatherDTO>
)

@Serializable
data class TemperatureDTO(
   val min: Double,
   val max: Double
)

@Serializable
data class WeatherDTO(
   val id: Int,
   val main: String,
   val description: String,
   val icon: String
)