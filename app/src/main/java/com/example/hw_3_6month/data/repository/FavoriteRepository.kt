package com.example.hw_3_6month.data.repository

import com.example.hw_3_6month.data.room.FavoriteCharacterDao
import com.example.hw_3_6month.data.room.FavoriteCharacterEntity
import kotlinx.coroutines.flow.Flow

class FavoritesRepository(private val dao: FavoriteCharacterDao) {

    fun getFavorites(): Flow<List<FavoriteCharacterEntity>> = dao.getAllFavorites()

    suspend fun add(character: FavoriteCharacterEntity) = dao.addToFavorites(character)

    suspend fun remove(character: FavoriteCharacterEntity) = dao.removeFromFavorites(character)

    suspend fun isFavorite(id: Int): Boolean = dao.isFavorite(id)
}
