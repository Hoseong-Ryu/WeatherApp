package com.devhoseong.weatherapp.searchcity

import com.devhoseong.domain.model.City
import com.devhoseong.domain.usecase.Failure


/**
 * UI State that represents SearchCityScreen
 **/
sealed interface SearchState {
    data object Empty : SearchState
    data object Loading : SearchState
    data class Success(
        val cities: List<City> = emptyList()
    ) : SearchState
    data class Error(val failure: Failure) : SearchState
}

/**
 * SearchCity Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class SearchActions(
    val onCityClicked: (City) -> Unit = {},
    val onSearchQueryChanged: (String) -> Unit = {}
)


