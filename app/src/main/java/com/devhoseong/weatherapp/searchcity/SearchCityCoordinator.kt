package com.devhoseong.weatherapp.searchcity

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.devhoseong.domain.model.City
import com.devhoseong.weatherapp.mapToFailure
import com.devhoseong.weatherapp.navigation.Screen
import kotlinx.coroutines.flow.combine

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class SearchCityCoordinator(
    val viewModel: SearchCityViewModel,
    val navController: NavController,
) {
    private var query = ""
    val screenStateFlow = combine(
        viewModel.stateFlow,
        viewModel.error
    ) { state, error ->
        when {
            error != null -> SearchState.Error(error.mapToFailure())
            state.isNotEmpty() -> SearchState.Success(state)
            query.isEmpty() && state.isEmpty() -> SearchState.Loading
            else -> SearchState.Empty
        }
    }

    fun searchCity(query: String) {
        this.query = query
        viewModel.searchCity(query)
    }

    fun navigateToMain(city: City) {
        with(city){
            navController.navigate(Screen.Main(
                id = id,
                name = name,
                country = country,
                lat = lat,
                lon = lon
            )){
                popUpTo(0){ inclusive = true }
            }
        }
    }

}

@Composable
fun rememberSearchCityCoordinator(
    viewModel: SearchCityViewModel = hiltViewModel(),
    navController: NavController = rememberNavController(),
): SearchCityCoordinator {
    return remember(viewModel) {
        SearchCityCoordinator(
            viewModel = viewModel,
            navController = navController
        )
    }
}