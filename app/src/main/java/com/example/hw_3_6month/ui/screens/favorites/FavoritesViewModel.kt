package com.example.hw_3_6month.ui.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw_3_6month.data.repository.FavoritesRepository
import com.example.hw_3_6month.data.room.FavoriteCharacterEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class FavoritesViewModel(private val repository: FavoritesRepository) : ViewModel() {

    val favoriteCharacters: StateFlow<List<FavoriteCharacterEntity>> =
        repository.getFavorites()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addToFavorites(character: FavoriteCharacterEntity) {
        viewModelScope.launch {
            repository.add(character)
        }
    }

    fun removeFromFavorites(character: FavoriteCharacterEntity) {
        viewModelScope.launch {
            repository.remove(character)
        }
    }
}

