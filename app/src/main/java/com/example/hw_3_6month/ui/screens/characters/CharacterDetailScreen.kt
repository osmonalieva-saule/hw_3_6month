package com.example.hw_3_6month.ui.screens.characters

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.hw_3_6month.data.room.FavoriteCharacterEntity
import com.example.hw_3_6month.model.Character
import com.example.hw_3_6month.ui.screens.favorites.FavoritesViewModel
import com.example.hw_3_6month.ui.theme.DarkGray
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.getViewModel

@Composable
fun CharacterDetailScreen(
    characterId: Int,
    viewModel: CharacterDetailViewModel = getViewModel()
) {
    val isFavorite by remember { mutableStateOf(false) }
    val favoritesViewModel: FavoritesViewModel = getViewModel()

    LaunchedEffect(key1 = characterId) {
        viewModel.loadCharacter(characterId)
    }

    val character by viewModel.character.collectAsState()

    if (character == null) {
        Text("Ошибка: персонаж не найден")
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGray),
    ) {
        AsyncImage(
            model = character!!.image,
            contentDescription = character!!.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(6.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            Text(text = character!!.name.toString(), fontSize = 50.sp)
            Text(text = "Status: ${character!!.status}", fontSize = 18.sp)
            Text(text = "Species: ${character!!.species}", fontSize = 18.sp)
            Text(text = "Gender: ${character!!.gender}", fontSize = 18.sp)
            Text(text = "Location: ${character!!.location}", fontSize = 18.sp)
        }
        IconButton(onClick = {
            character!!.id?.let { character!!.name?.let { it1 ->
                character!!.image?.let { it2 ->
                    FavoriteCharacterEntity(it,
                        it1, it2
                    )
                }
            } }?.let {
                favoritesViewModel.addToFavorites(
                    it
                )
            }
        }) {
            Icon(Icons.Default.Favorite, contentDescription = "Add to favorites")
        }
    }
}
