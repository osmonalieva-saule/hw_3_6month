package com.example.hw_3_6month.ui.Screens.characters

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.hw_3_6month.model.Character
import com.example.hw_3_6month.ui.theme.DarkGray
import kotlinx.serialization.json.Json

@Composable
fun CharacterDetailScreen(characterJson: String?, navController: NavController) {
    val character = characterJson?.let {
        Json.decodeFromString<Character>(Uri.decode(it))
    }

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
            model = character.image,
            contentDescription = character.name,
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
            Text(text = character.name.toString(), fontSize = 50.sp)
            Text(text = "Status: ${character.status}", fontSize = 18.sp)
            Text(text = "Species: ${character.species}", fontSize = 18.sp)
            Text(text = "Gender: ${character.gender}", fontSize = 18.sp)
            Text(text = "Location: ${character.location}", fontSize = 18.sp)
        }
    }
}