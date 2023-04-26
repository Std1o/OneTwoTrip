package com.stdio.onetwotrip.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stdio.onetwotrip.data.MainRepository
import com.stdio.onetwotrip.domain.DataState
import com.stdio.onetwotrip.domain.models.AirportInfoResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketDetailsViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val _uiStateFrom = MutableStateFlow<DataState<AirportInfoResponse>>(DataState.Loading)
    val uiStateFrom: StateFlow<DataState<AirportInfoResponse>> = _uiStateFrom.asStateFlow()

    private val _uiStateTo = MutableStateFlow<DataState<AirportInfoResponse>>(DataState.Loading)
    val uiStateTo: StateFlow<DataState<AirportInfoResponse>> = _uiStateTo.asStateFlow()

    fun getAirportInfo(from: String, to: String) {
        viewModelScope.launch {
            val airportFromInfoCall = async { repository.getAirportInfo(from) }
            val airportToInfoCall = async { repository.getAirportInfo(to) }
            airportFromInfoCall.await().zip(airportToInfoCall.await()) { first, second ->
                Pair(first, second)
            }.collect { pair->
                _uiStateFrom.value = pair.first
                _uiStateTo.value = pair.second
            }
        }
    }
}