package com.example.hw_3_6month.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorite_characters")
data class FavoriteCharacterEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val image: String
)

