package com.devhoseong.weatherapp.mainweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devhoseong.domain.model.City
import com.devhoseong.domain.usecase.GetWeatherUseCase
import com.devhoseong.weatherapp.mapToFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainWeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val state = _state.asStateFlow()

    init {
        // 초기 데이터로 아산 날씨 로드
        getWeather(
            City(
                id = 1839726,
                name = "Asan",
                country = "KR",
                lat = 36.783611,
                lon = 127.004173
            )
        )
    }

    fun getWeather(city: City) {
        if (state.value is WeatherState.Success && (city == (state.value as WeatherState.Success).city)) {
            return
        }
        viewModelScope.launch {
            _state.value = WeatherState.Loading
            getWeatherUseCase(city.lat, city.lon)
                .onSuccess { weather ->
                    _state.value = WeatherState.Success(
                        weather = weather,
                        city = city
                    )
                }
                .onFailure { throwable ->
                    _state.value = WeatherState.Error(throwable.mapToFailure())
                }
        }
    }
}