package com.devhoseong.weatherapp.mainweather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.devhoseong.domain.model.City
import com.devhoseong.weatherapp.isValid


@Composable
fun MainWeatherRoute(
    coordinator: MainWeatherCoordinator = rememberMainWeatherCoordinator(),
    city: City?,
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(WeatherState.Loading)

    // UI Actions
    val actions = rememberMainWeatherActions(coordinator)

    LaunchedEffect(city) {
        if (city != null && city.isValid()) {
            actions.getWeather(city)
        }
    }

    // UI Rendering
    MainWeatherScreen(uiState, actions)
}


@Composable
fun rememberMainWeatherActions(coordinator: MainWeatherCoordinator): MainWeatherActions {
    return remember(coordinator) {
        MainWeatherActions(
            getWeather = coordinator::getWeather,
            navigateToSearch = coordinator::navigateToSearch
        )
    }
}
