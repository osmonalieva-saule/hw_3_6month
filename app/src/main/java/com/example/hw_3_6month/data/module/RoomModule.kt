package com.example.hw_3_6month.data.module

import androidx.room.Room
import com.example.hw_3_6month.data.repository.FavoritesRepository
import com.example.hw_3_6month.data.room.AppDatabase
import com.example.hw_3_6month.ui.screens.favorites.FavoritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "app_db").build()
    }

    single { get<AppDatabase>().favoriteCharacterDao() }
    single { FavoritesRepository(get()) }
    viewModel { FavoritesViewModel(get()) }
}