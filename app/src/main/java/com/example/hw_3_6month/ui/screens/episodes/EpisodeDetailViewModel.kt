package com.example.hw_3_6month.ui.screens.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw_3_6month.data.dto.episode.EpisodeDto
import com.example.hw_3_6month.data.repository.EpisodeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EpisodeDetailViewModel(private val repository: EpisodeRepository) : ViewModel() {
    private val _episode = MutableStateFlow<EpisodeDto?>(null)
    val episode: StateFlow<EpisodeDto?> = _episode

    fun loadEpisode(id: Int) {
        viewModelScope.launch {
            _episode.value = repository.fetchEpisodeById(id)
        }
    }
}