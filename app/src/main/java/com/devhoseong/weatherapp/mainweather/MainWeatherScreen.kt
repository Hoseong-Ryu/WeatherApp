package com.devhoseong.weatherapp.mainweather

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainWeatherScreen(
    state: WeatherState,
    actions: MainWeatherActions
) {
    // TODO UI Rendering
}

@Composable
@Preview(name = "MainWeather")
private fun MainWeatherScreenPreview() {
    MainWeatherScreen(
        state = WeatherState.Loading,
        actions = MainWeatherActions()
    )
}
