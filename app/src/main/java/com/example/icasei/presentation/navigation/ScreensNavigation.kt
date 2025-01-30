package com.example.icasei.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.icasei.presentation.favorites.FavoritesScreen
import com.example.icasei.presentation.home.HomeScreen
import com.example.icasei.presentation.playlist.PlaylistScreen

fun NavGraphBuilder.homeScreen() {
    composable(
        route = ScreensDestinations.HomeScreen.route,
    ) {
        HomeScreen()
    }
}

fun NavHostController.navigateToHome() {
    navigate(ScreensDestinations.HomeScreen.route)
}

fun NavGraphBuilder.favoritesScreen() {
    composable(
        route = ScreensDestinations.FavoritesScreen.route,
    ) {
        FavoritesScreen()
    }
}

fun NavHostController.navigateToFavorites() {
    navigate(ScreensDestinations.FavoritesScreen.route)
}

fun NavGraphBuilder.playlistsScreen() {
    composable(
        route = ScreensDestinations.PlaylistsScreen.route,
    ) {
        PlaylistScreen()
    }
}

fun NavHostController.navigateToPlaylists() {
    navigate(ScreensDestinations.PlaylistsScreen.route)
}
