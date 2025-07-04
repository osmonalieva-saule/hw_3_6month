package com.example.hw_3_6month.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteCharacterEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteCharacterDao(): FavoriteCharacterDao
}

