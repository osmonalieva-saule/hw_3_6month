package com.example.hw_3_6month.ui.screens.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw_3_6month.data.repository.CharacterRepository
import com.example.hw_3_6month.model.Character
import com.example.hw_3_6month.model.Status.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            val response = repository.fetchAllCharacters()
            _characters.value = response?.results?.map { dto ->
                Character(
                    id = dto.id ?: -1,
                    name = dto.name ?: "Unknown",
                    gender = dto.gender ?: "Unknown",
                    status = when (dto.status?.lowercase()) {
                        "alive" -> Alive
                        "dead" -> Dead
                        else -> unknown
                    },
                    species = "Unknown",
                    image = dto.image,          // пока нет в dto
                    location = dto.location.name ?: "Unknown"
                )
            } ?: emptyList()
        }
    }
}
