package com.devhoseong.data.mapper

import com.devhoseong.data.remote.model.CurrentDTO
import com.devhoseong.data.remote.model.DailyDTO
import com.devhoseong.data.remote.model.HourlyDTO
import com.devhoseong.data.remote.model.WeatherResponse
import com.devhoseong.domain.model.CurrentWeather
import com.devhoseong.domain.model.DailyForecast
import com.devhoseong.domain.model.HourlyForecast
import com.devhoseong.domain.model.Weather

fun WeatherResponse.mapToWeather(): Weather {
    return Weather(
        currentWeather = current.mapToCurrentWeather(daily.first()),
        hourlyForecasts = hourly.map { it.mapToHourlyForecast() },
        dailyForecasts = daily.map { it.mapToDailyForecast() }
    )
}

fun CurrentDTO.mapToCurrentWeather(daily: DailyDTO): CurrentWeather {
    return CurrentWeather(
        temperature = temp,
        minTemp = daily.temperature.min,
        maxTemp = daily.temperature.max,
        humidity = humidity,
        windSpeed = windSpeed,
        clouds = clouds,
        weatherIcon = weather.firstOrNull()?.icon.orEmpty(),
        weatherDescription = weather.firstOrNull()?.description.orEmpty()
    )
}

private fun HourlyDTO.mapToHourlyForecast(): HourlyForecast {
    return HourlyForecast(
        timestamp = dt,
        temperature = temp,
        weatherIcon = weather.firstOrNull()?.icon.orEmpty()
    )
}

private fun DailyDTO.mapToDailyForecast(): DailyForecast {
    return DailyForecast(
        timestamp = dt,
        minTemp = temperature.min,
        maxTemp = temperature.max,
        weatherIcon = weather.firstOrNull()?.icon.orEmpty()
    )
}