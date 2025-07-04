package com.example.hw_3_6month.ui.screens.locations

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel

@Composable
fun LocationsScreen(
    viewModel: LocationListViewModel = getViewModel(),
    navController: NavController
) {
    val locations by viewModel.locations.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadLocations()
    }

    LazyColumn {
        items(locations) { location ->
            location.name?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("location_detail/${location.id}")
                        }
                        .padding(16.dp)
                )
            }
        }
    }
}
