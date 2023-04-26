package com.stdio.onetwotrip.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.stdio.onetwotrip.data.MainRepository
import com.stdio.onetwotrip.domain.models.Ticket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : BaseViewModel<List<Ticket>>() {

    init {
        getTickets()
    }

    private fun getTickets() {
        viewModelScope.launch {
            launchRequest(repository.getTickets())
        }
    }
}