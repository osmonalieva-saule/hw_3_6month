package com.example.hw_3_6month

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.hw_3_6month.ui.navigation.App
import com.example.hw_3_6month.ui.theme.Hw_3_6monthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hw_3_6monthTheme {
                App()
            }
        }
    }
}