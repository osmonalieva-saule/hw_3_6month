package com.example.hw_3_6month.data.repository

import com.example.hw_3_6month.data.api.CharacterApiService
import com.example.hw_3_6month.data.dto.character.ResponseCharacter
import com.example.hw_3_6month.data.dto.character.CharacterDTO

class CharacterRepository(
    private val characterApiService: CharacterApiService
) {
    suspend fun fetchAllCharacters(): ResponseCharacter? {
        val response = characterApiService.fetchAllCharacters()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    suspend fun fetchCharacterById(id: Int): CharacterDTO? {
        val response = characterApiService.fetchCharacterById(id)
        return if (response.isSuccessful) response.body() else null
    }

}