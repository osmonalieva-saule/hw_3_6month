package com.example.hw_3_6month.ui.screens.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw_3_6month.data.dto.location.LocationDTO
import com.example.hw_3_6month.data.repository.LocationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocationDetailViewModel(private val repository: LocationRepository) : ViewModel() {
    private val _location = MutableStateFlow<LocationDTO?>(null)
    val location: StateFlow<LocationDTO?> = _location

    fun loadLocation(id: Int) {
        viewModelScope.launch {
            _location.value = repository.fetchLocationById(id)
        }
    }
}