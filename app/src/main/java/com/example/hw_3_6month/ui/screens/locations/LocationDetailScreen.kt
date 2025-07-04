package com.example.hw_3_6month.ui.screens.locations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel

@Composable
fun LocationDetailScreen(
    locationId: Int,
    viewModel: LocationDetailViewModel = getViewModel()
) {
    LaunchedEffect(locationId) {
        viewModel.loadLocation(locationId)
    }

    val location by viewModel.location.collectAsState()

    if (location == null) {
        Text("Ошибка: локация не найдена")
        return
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Название: ${location!!.name}", fontSize = 24.sp)
        Text(text = "Тип: ${location!!.type}", fontSize = 18.sp)
        Text(text = "Измерение: ${location!!.dimension}", fontSize = 18.sp)
    }
}
