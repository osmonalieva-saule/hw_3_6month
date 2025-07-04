package com.example.hw_3_6month.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCharacterDao {

    @Query("SELECT * FROM favorite_characters")
    fun getAllFavorites(): Flow<List<FavoriteCharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(character: FavoriteCharacterEntity)

    @Delete
    suspend fun removeFromFavorites(character: FavoriteCharacterEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_characters WHERE id = :id)")
    suspend fun isFavorite(id: Int): Boolean
}

