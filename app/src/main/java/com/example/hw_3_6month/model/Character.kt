package com.example.hw_3_6month.model

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: Int? = null,
    val name: String? = null,
    val image: String? = null,
    val status: Status,
    val species: String? = null,
    val gender: String? = null,
    val location: String? = null,
)

enum class Status {
    Alive, Dead, unknown
}