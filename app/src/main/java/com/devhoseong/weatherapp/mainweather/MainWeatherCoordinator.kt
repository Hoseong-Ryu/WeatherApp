package com.devhoseong.weatherapp.mainweather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.devhoseong.domain.model.City
import com.devhoseong.weatherapp.navigation.Screen

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class MainWeatherCoordinator(
    private val viewModel: MainWeatherViewModel,
    private val navController: NavController,
) {
    val screenStateFlow = viewModel.state

    fun getWeather(city: City) {
        viewModel.getWeather(city)
    }

    fun navigateToSearch() {
        navController.navigate(Screen.Search)
    }
}

@Composable
fun rememberMainWeatherCoordinator(
    viewModel: MainWeatherViewModel = hiltViewModel(),
    navController: NavController = rememberNavController(),
): MainWeatherCoordinator {
    return remember(viewModel) {
        MainWeatherCoordinator(
            viewModel = viewModel,
            navController = navController
        )
    }
}