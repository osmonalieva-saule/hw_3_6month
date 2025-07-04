package com.example.hw_3_6month.ui.screens.episodes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.getViewModel

@Composable
fun EpisodeDetailScreen(episodeId: Int, viewModel: EpisodeDetailViewModel = getViewModel()) {
    LaunchedEffect(episodeId) {
        viewModel.loadEpisode(episodeId)
    }

    val episode by viewModel.episode.collectAsState()

    if (episode == null) {
        Text("Ошибка: эпизод не найден")
        return
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Название: ${episode!!.name}", fontSize = 24.sp)
        Text(text = "Эпизод: ${episode!!.episode}")
        Text(text = "Дата выхода: ${episode!!.air_date}")
    }
}
