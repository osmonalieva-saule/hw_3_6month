package com.example.hw_3_6month.data.dto.character

data class CharacterDTO(
    val id: Int? = -1,
    val name: String? = "",
    val gender: String? = "",
    val status: String? = "",
    val location: LocationDTO
)