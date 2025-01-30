package com.example.icasei.presentation.navigation

sealed class ScreensDestinations(val route: String) {
    data object HomeScreen : ScreensDestinations("home_screen")
    data object FavoritesScreen : ScreensDestinations("favorites_screen")
    data object PlaylistsScreen : ScreensDestinations("playlists_screen")
}
