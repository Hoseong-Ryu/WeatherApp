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
import com.devhoseong.domain.model.City
import com.devhoseong.domain.model.Weather
import com.devhoseong.domain.usecase.Failure
import com.devhoseong.weatherapp.mainweather.components.CurrentWeatherSection
import com.devhoseong.weatherapp.mainweather.components.DailyForecastSection
import com.devhoseong.weatherapp.mainweather.components.HourlySection

@Composable
fun MainWeatherScreen(
    state: WeatherState,
    actions: MainWeatherActions
) {
    when (state) {
        is WeatherState.Loading -> LoadingScreen()
        is WeatherState.Success -> WeatherContent(
            city = state.city,
            weather = state.weather
        )
        is WeatherState.Error -> ErrorScreen(error = state.failure)
    }
}

@Composable
private fun WeatherContent(
    city: City?,
    weather: Weather?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = city?.name.orEmpty(),
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(Modifier.height(16.dp))

        CurrentWeatherSection(
            weather = weather,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(16.dp))

        HourlySection(
            hourlyForecasts = weather?.hourlyForecasts.orEmpty(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        DailyForecastSection(
            dailyForecasts = weather?.dailyForecasts.orEmpty(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorScreen(error: Failure) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Error: $error")
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
