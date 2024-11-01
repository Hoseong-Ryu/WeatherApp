package com.devhoseong.weatherapp.mainweather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.devhoseong.domain.usecase.Failure
import kotlinx.coroutines.flow.combine
import retrofit2.HttpException
import java.io.IOException

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class MainWeatherCoordinator(
    private val viewModel: MainWeatherViewModel,
) {
    val screenStateFlow = combine(
        viewModel.weatherState,
        viewModel.city,
        viewModel.error
    ) { weather, city, error ->
        when {
            error != null -> WeatherState.Error(error.mapToFailure())
            weather != null -> WeatherState.Success(
                weather = weather,
                city = city
            )
            else -> WeatherState.Loading
        }
    }

    private fun Throwable.mapToFailure(): Failure {
        return when (this) {
            is IOException -> Failure.NetworkError
            is HttpException -> Failure.ApiError(code(), message())
            else -> Failure.UnknownError
        }
    }

}

@Composable
fun rememberMainWeatherCoordinator(
    viewModel: MainWeatherViewModel = hiltViewModel()
): MainWeatherCoordinator {
    return remember(viewModel) {
        MainWeatherCoordinator(
            viewModel = viewModel,
        )
    }
}