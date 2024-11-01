package com.devhoseong.weatherapp.mainweather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember


@Composable
fun MainWeatherRoute(
    coordinator: MainWeatherCoordinator = rememberMainWeatherCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(WeatherState.Loading)

    // UI Actions
    val actions = rememberMainWeatherActions(coordinator)

    // UI Rendering
    MainWeatherScreen(uiState, actions)
}


@Composable
fun rememberMainWeatherActions(coordinator: MainWeatherCoordinator): MainWeatherActions {
    return remember(coordinator) {
        MainWeatherActions(

        )
    }
}
