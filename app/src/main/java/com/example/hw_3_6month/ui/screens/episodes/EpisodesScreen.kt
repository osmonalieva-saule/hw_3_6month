package com.example.hw_3_6month.ui.screens.episodes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel

@Composable
fun EpisodesScreen(viewModel: EpisodeListViewModel = getViewModel(), navController: NavController) {
    val episodes by viewModel.episodes.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadEpisodes()
    }

    LazyColumn {
        items(episodes) { episode ->
            Text(
                text = episode.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("episode_detail/${episode.id}")
                    }
                    .padding(16.dp)
            )
        }
    }
}
