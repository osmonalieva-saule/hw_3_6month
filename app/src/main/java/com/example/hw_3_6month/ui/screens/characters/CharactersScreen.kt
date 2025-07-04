package com.example.hw_3_6month.ui.screens.characters

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.graphics.ColorFilter.Companion
import coil.compose.rememberAsyncImagePainter
import com.example.hw_3_6month.R
import com.example.hw_3_6month.ui.navigation.Screen
import com.example.hw_3_6month.ui.theme.Gray
import com.example.hw_3_6month.model.Character
import com.example.hw_3_6month.model.Status
import com.example.hw_3_6month.ui.theme.DarkGray
import org.koin.androidx.compose.getViewModel

@Composable
fun CharactersScreen(navController: NavController,
                     viewModel: CharacterViewModel = getViewModel()
) {
    val characters by viewModel.characters.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGray)
    ) {
        items(characters) { character ->
            CharactersItem(character = character, navController = navController)
        }
    }
}

@Composable
fun CharactersItem(
    character: Character,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Gray
        ),
        onClick = {
            navController.navigate("character_detail/${character.id}")
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .width(148.dp)
                    .fillMaxHeight(),
                contentDescription = "Character image",
                contentScale = ContentScale.Crop,
                painter = rememberAsyncImagePainter(character.image),
            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center

            ) {
                Text(
                    text = character.name.toString(), color = White, fontSize = 26.sp
                )
                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(16.dp)
                            .clip(CircleShape),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        painter = rememberAsyncImagePainter(
                            R.drawable.circle
                        ),
                        colorFilter = when (character.status) {
                            Status.Dead -> Companion.tint(Red)
                            Status.Alive -> Companion.tint(Green)
                            Status.unknown -> Companion.tint(Gray)
                        }

                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "${character.status.name} - ${character.species}",
                        color = White,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharactersScreenPreview() {
    CharactersScreen(navController = NavController(LocalContext.current))
}