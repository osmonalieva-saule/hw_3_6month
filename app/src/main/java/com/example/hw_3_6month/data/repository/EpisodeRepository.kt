package com.example.hw_3_6month.data.repository

import com.example.hw_3_6month.data.api.CharacterApiService
import com.example.hw_3_6month.data.dto.episode.EpisodeDto

class EpisodeRepository(private val apiService: CharacterApiService) {
    suspend fun fetchEpisodes(): List<EpisodeDto> {
        return apiService.getEpisodes().results
    }

    suspend fun fetchEpisodeById(id: Int): EpisodeDto? {
        return apiService.getEpisodeById(id)
    }
}