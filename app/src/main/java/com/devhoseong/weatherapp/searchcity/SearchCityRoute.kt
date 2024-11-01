package com.devhoseong.weatherapp.searchcity

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember


@Composable
fun SearchCityRoute(
    coordinator: SearchCityCoordinator = rememberSearchCityCoordinator(),
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(SearchState.Loading)

    // UI Actions
    val actions = rememberSearchCityActions(coordinator)

    // UI Rendering
    SearchCityScreen(uiState, actions)
}


@Composable
fun rememberSearchCityActions(coordinator: SearchCityCoordinator): SearchActions {
    return remember(coordinator) {
        SearchActions(
            onCityClicked = coordinator::navigateToMain,
            onSearchQueryChanged = coordinator::searchCity
        )
    }
}
