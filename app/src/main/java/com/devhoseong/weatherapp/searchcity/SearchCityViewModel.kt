package com.devhoseong.weatherapp.searchcity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devhoseong.domain.model.City
import com.devhoseong.domain.usecase.SearchCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchCityViewModel @Inject constructor(
    private val searchCitiesUseCase: SearchCitiesUseCase,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<List<City>> = MutableStateFlow(emptyList())

    val stateFlow: StateFlow<List<City>> = _stateFlow.asStateFlow()

    private val _error = MutableStateFlow<Throwable?>(null)
    val error = _error.asStateFlow()

    private val searchQuery = MutableStateFlow("")

    init {
        viewModelScope.launch {
            searchQuery
                .debounce(500L)
                .collect { query ->
                    searchCity(query)
                }
        }
    }

    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }

    private fun searchCity(query: String) {
        viewModelScope.launch {
            searchCitiesUseCase(query)
                .onSuccess {
                    _stateFlow.value = it
                }.onFailure {
                    _error.value = it
                }
        }
    }


}