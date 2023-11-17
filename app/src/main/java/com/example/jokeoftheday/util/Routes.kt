package com.example.jokeoftheday.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Routes(
    val route: String,
    val icon: ImageVector,
    val name: String
) {
    object Main: Routes(route = "Main", Icons.Default.Home, name = "Joke page")
    object Library: Routes(route = "List", Icons.Default.List, name = "Saved history")
    object About: Routes(route = "About", Icons.Default.Info, name = "About developer")
}

