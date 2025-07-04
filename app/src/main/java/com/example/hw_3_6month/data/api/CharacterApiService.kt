package com.example.hw_3_6month.data.api

import com.example.hw_3_6month.data.dto.episode.EpisodesResponseDto
import com.example.hw_3_6month.data.dto.location.LocationsResponseDto
import com.example.hw_3_6month.data.dto.character.ResponseCharacter
import com.example.hw_3_6month.data.dto.character.CharacterDTO
import com.example.hw_3_6month.data.dto.episode.EpisodeDto
import com.example.hw_3_6month.data.dto.location.LocationDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApiService {
    @GET("character")
    suspend fun fetchAllCharacters(): Response<ResponseCharacter>

    @GET("character/{id}")
    suspend fun fetchCharacterById(@Path("id") id: Int): Response<CharacterDTO>

    @GET("episode")
    suspend fun getEpisodes(): EpisodesResponseDto

    @GET("episode/{id}")
    suspend fun getEpisodeById(@Path("id") id: Int): EpisodeDto?

    // Locations
    @GET("location")
    suspend fun getLocations(): LocationsResponseDto

    @GET("location/{id}")
    suspend fun getLocationById(@Path("id") id: Int): LocationDTO?
}