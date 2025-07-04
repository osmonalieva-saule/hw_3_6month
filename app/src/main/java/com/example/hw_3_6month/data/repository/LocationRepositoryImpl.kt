package com.example.hw_3_6month.data.repository

import com.example.hw_3_6month.data.api.CharacterApiService
import com.example.hw_3_6month.data.dto.location.LocationDTO

class LocationRepositoryImpl(
    private val apiService: CharacterApiService
) : LocationRepository {
    override suspend fun fetchLocations(): List<LocationDTO> {
        return apiService.getLocations().results
    }

    override suspend fun fetchLocationById(id: Int): LocationDTO? {
        return apiService.getLocationById(id)
    }
}