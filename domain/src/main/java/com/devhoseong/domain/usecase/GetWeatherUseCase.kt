package com.devhoseong.domain.usecase

import com.devhoseong.domain.model.Weather
import com.devhoseong.domain.reposiroty.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double, lon: Double): Result<Weather> {
        return weatherRepository.getWeather(lat, lon)
    }
}