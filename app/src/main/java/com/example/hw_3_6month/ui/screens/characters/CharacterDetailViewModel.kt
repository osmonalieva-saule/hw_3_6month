package com.example.hw_3_6month.ui.screens.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw_3_6month.data.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.hw_3_6month.model.Character
import com.example.hw_3_6month.model.Status

class CharacterDetailViewModel(
    private val repository: CharacterRepository
) : ViewModel() {
    private val _character = MutableStateFlow<Character?>(null)
    val character: StateFlow<Character?> = _character

    fun loadCharacter(id: Int) {
        viewModelScope.launch {
            val dto = repository.fetchCharacterById(id)
            _character.value = dto?.let {
                Character(
                    id = it.id,
                    name = it.name,
                    status = when (it.status?.lowercase()) {
                        "alive" -> Status.Alive
                        "dead" -> Status.Dead
                        else -> Status.unknown
                    },
                    gender = it.gender,
                    species = "Unknown",
                    image = it.image ?: "",
                    location = it.location.name ?: ""
                )
            }
        }
    }
}
