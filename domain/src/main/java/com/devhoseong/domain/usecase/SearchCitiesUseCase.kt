package com.devhoseong.domain.usecase

import com.devhoseong.domain.model.City
import com.devhoseong.domain.reposiroty.WeatherRepository
import javax.inject.Inject

class SearchCitiesUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(query: String): Result<List<City>> {
        return weatherRepository.getCityWeatherInfo(query)
    }
}