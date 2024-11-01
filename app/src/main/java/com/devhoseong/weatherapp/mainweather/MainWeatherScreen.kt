package com.devhoseong.weatherapp.mainweather

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devhoseong.weatherapp.mainweather.components.CurrentWeatherSection
import com.devhoseong.weatherapp.mainweather.components.HourlySection

@Composable
fun MainWeatherScreen(
    state: WeatherState,
    actions: MainWeatherActions
) {
    when (state) {
        is WeatherState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is WeatherState.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = state.city?.name.orEmpty(),
                    style = MaterialTheme.typography.titleLarge,
                )
                // 현재 날씨 섹션
                CurrentWeatherSection(
                    weather = state.weather,
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(Modifier.height(16.dp))

                HourlySection(
                    hourlyForecasts = state.weather?.hourlyForecasts.orEmpty(),
                    modifier = Modifier.fillMaxWidth()
                )

            }
        }
        is WeatherState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error: ${state.failure}")
            }
        }
    }
}

@Composable
@Preview(name = "MainWeather")
private fun MainWeatherScreenPreview() {
    MainWeatherScreen(
        state = WeatherState.Loading,
        actions = MainWeatherActions()
    )
}
