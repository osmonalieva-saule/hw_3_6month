package com.example.hw_3_6month.ui.screens.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw_3_6month.data.dto.episode.EpisodeDto
import com.example.hw_3_6month.data.repository.EpisodeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EpisodeListViewModel(private val repository: EpisodeRepository) : ViewModel() {
    private val _episodes = MutableStateFlow<List<EpisodeDto>>(emptyList())
    val episodes: StateFlow<List<EpisodeDto>> = _episodes

    fun loadEpisodes() {
        viewModelScope.launch {
            val list = repository.fetchEpisodes()
            _episodes.value = list
        }
    }
}