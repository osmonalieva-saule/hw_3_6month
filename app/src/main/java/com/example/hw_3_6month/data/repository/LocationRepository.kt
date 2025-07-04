package com.example.hw_3_6month.data.repository

import com.example.hw_3_6month.data.dto.location.LocationDTO

interface LocationRepository {
    suspend fun fetchLocations(): List<LocationDTO>
    suspend fun fetchLocationById(id: Int): LocationDTO?
}
