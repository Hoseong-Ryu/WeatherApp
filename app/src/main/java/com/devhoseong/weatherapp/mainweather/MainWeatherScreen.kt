package com.devhoseong.weatherapp.mainweather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devhoseong.domain.model.City
import com.devhoseong.domain.model.Weather
import com.devhoseong.domain.usecase.Failure
import com.devhoseong.weatherapp.mainweather.components.CurrentWeatherSection
import com.devhoseong.weatherapp.mainweather.components.DailyForecastSection
import com.devhoseong.weatherapp.mainweather.components.DayWeatherSection
import com.devhoseong.weatherapp.mainweather.components.HourlySection
import com.devhoseong.weatherapp.mainweather.components.MapSection

@Composable
fun MainWeatherScreen(
    state: WeatherState,
    actions: MainWeatherActions
) {
    when (state) {
        is WeatherState.Loading -> LoadingScreen()
        is WeatherState.Success -> WeatherContent(
            city = state.city,
            weather = state.weather,
            actions = actions
        )
        is WeatherState.Error -> ErrorScreen(error = state.failure)
    }
}

@Composable
private fun WeatherContent(
    city: City?,
    weather: Weather?,
    actions: MainWeatherActions,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = city?.name.orEmpty(),
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
                    .padding(end = 8.dp)
            )

            IconButton(onClick = actions.navigateToSearch) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "도시 검색"
                )
            }
        }

        CurrentWeatherSection(
            weather = weather,
            modifier = Modifier.fillMaxWidth(),
        )

        HourlySection(
            hourlyForecasts = weather?.hourlyForecasts.orEmpty(),
            modifier = Modifier.fillMaxWidth()
        )

        DailyForecastSection(
            dailyForecasts = weather?.dailyForecasts.orEmpty(),
            modifier = Modifier.fillMaxWidth()
        )

        MapSection(city = city, modifier = Modifier.
        fillMaxWidth().height(270.dp))

        DayWeatherSection(weather, modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(10.dp))
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
