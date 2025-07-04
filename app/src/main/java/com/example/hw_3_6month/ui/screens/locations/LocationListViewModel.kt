package com.example.hw_3_6month.ui.screens.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw_3_6month.data.dto.location.LocationDTO
import com.example.hw_3_6month.data.repository.LocationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocationListViewModel(private val repository: LocationRepository) : ViewModel() {
    private val _locations = MutableStateFlow<List<LocationDTO>>(emptyList())
    val locations: StateFlow<List<LocationDTO>> = _locations

    fun loadLocations() {
        viewModelScope.launch {
            val list = repository.fetchLocations()
            _locations.value = list
        }
    }
}