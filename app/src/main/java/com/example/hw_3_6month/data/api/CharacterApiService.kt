package com.example.hw_3_6month.data.api

import com.example.hw_3_6month.data.dto.ResponseCharacter
import retrofit2.Response
import retrofit2.http.GET

interface CharacterApiService {
    @GET("character")
    suspend fun fetchAllCharacters(): Response<ResponseCharacter>
}