package com.example.hw_3_6month.ui.screens.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel = getViewModel()) {
    val favorites by viewModel.favoriteCharacters.collectAsState()

    LazyColumn {
        items(favorites) { character ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        // Переход на Detail
                    }
                    .padding(16.dp)
            ) {
                AsyncImage(
                    model = character.image,
                    contentDescription = character.name,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = character.name, fontSize = 20.sp)
            }
        }
    }
}

