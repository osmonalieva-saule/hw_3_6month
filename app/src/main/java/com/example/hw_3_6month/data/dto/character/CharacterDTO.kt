package com.example.hw_3_6month.data.dto.character

import com.example.hw_3_6month.data.dto.location.LocationDTO

data class CharacterDTO(
    val id: Int? = -1,
    val name: String? = "",
    val status: String? = "",
    val species: String? = "",
    val gender: String? = "",
    val image: String? = "",
    val location: LocationDTO
)
