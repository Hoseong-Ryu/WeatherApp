package com.devhoseong.weatherapp.mainweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devhoseong.domain.model.City
import com.devhoseong.domain.model.Weather
import com.devhoseong.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainWeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
) : ViewModel() {

    private val _weatherState: MutableStateFlow<Weather?> =
        MutableStateFlow(null)
    val weatherState: StateFlow<Weather?> = _weatherState.asStateFlow()

    private val _cities = MutableStateFlow(
        listOf(
            City(
                id = 1839726,
                name = "Asan",
                country = "KR",
                lat = 36.783611,
                lon = 127.004173
            )
        )
    )
    val cities = _cities.asStateFlow()

    private val _error = MutableStateFlow<Throwable?>(null)
    val error = _error.asStateFlow()

    init {
        // 초기 데이터로 아산 날씨 로드
        getWeather(36.783611, 127.004173)
    }

    fun getWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            _error.value = null
            getWeatherUseCase(lat, lon)
                .onSuccess { weather ->
                    _weatherState.value = weather
                }
                .onFailure { throwable ->
                    _error.value = throwable
                }
        }
    }
}