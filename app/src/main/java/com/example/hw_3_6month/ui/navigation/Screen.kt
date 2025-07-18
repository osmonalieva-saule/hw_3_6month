package com.example.hw_3_6month.ui.navigation

sealed class Screen(val route: String, val title: String?) {
    data object Characters : Screen("characters", "Персонажи")
    data object Locations : Screen("locations", "Локации")
    data object Episodes : Screen("episodes", "Эпизоды")
    object CharacterDetail : Screen("character_detail/{characterId}", "Character Detail") {
        fun passId(id: Int) = "character_detail/$id"
    }
    data object LocationDetail : Screen("location_detail", "Детали локации")
    data object EpisodeDetail : Screen("episode_detail", "Детали эпизода")
}