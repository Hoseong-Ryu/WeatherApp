package com.devhoseong.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
   val lat: Double,
   val lon: Double,
   val timezone: String,
   val current: Current,
   val hourly: List<Hourly>,
   val daily: List<Daily>
)

@Serializable
data class Current(
   val dt: Long,
   val temp: Double,
   val humidity: Int,
   val clouds: Int,
   @SerialName("wind_speed") val windSpeed: Double,
   val weather: List<Weather>
)

@Serializable
data class Hourly(
   val dt: Long,
   val temp: Double,
   val weather: List<Weather>
)

@Serializable
data class Daily(
   val dt: Long,
   @SerialName("temp") 
   val temperature: Temperature,
   val weather: List<Weather>
)

@Serializable
data class Temperature(
   val min: Double,
   val max: Double
)

@Serializable
data class Weather(
   val id: Int,
   val main: String,
   val description: String,
   val icon: String
)