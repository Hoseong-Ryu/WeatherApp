package com.devhoseong.weatherapp.searchcity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devhoseong.domain.model.City
import com.devhoseong.domain.usecase.SearchCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCityViewModel @Inject constructor(
    private val searchCitiesUseCase: SearchCitiesUseCase,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<List<City>> = MutableStateFlow(emptyList())

    val stateFlow: StateFlow<List<City>> = _stateFlow.asStateFlow()

    private val _error = MutableStateFlow<Throwable?>(null)
    val error = _error.asStateFlow()

    init {
        searchCity("")
    }

    fun searchCity(query: String) {
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